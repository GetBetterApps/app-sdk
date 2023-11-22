package com.velkonost.getbetter.shared.features.calendars.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.CALENDARS_UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.datastore.USER_REGISTRATION_MILLIS
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.user.UserAction
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.DAY_MILLIS
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.todayMillis
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowLocalRequest
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.calendars.api.CalendarsRepository
import com.velkonost.getbetter.shared.features.calendars.api.model.DateDirection
import com.velkonost.getbetter.shared.features.calendars.api.model.DateItem
import com.velkonost.getbetter.shared.features.calendars.api.model.addDay
import com.velkonost.getbetter.shared.features.calendars.api.model.removeDay
import com.velkonost.getbetter.shared.features.calendars.data.remote.CalendarsRemoteDataSource
import com.velkonost.getbetter.shared.features.calendars.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CalendarsRepositoryImpl(
    private val localDataSource: DataStore<Preferences>,
    private val remoteDataSource: CalendarsRemoteDataSource
) : CalendarsRepository {

    override fun getInitItems(): Flow<ResultState<List<DateItem>>> = flowLocalRequest {
        val todayDate = DateItem(
            selectedByDefault = true,
            millis = todayMillis
        )

        val items = listOf(todayDate)
        val userRegistrationDate = localDataSource.data.first()[USER_REGISTRATION_MILLIS]
        val pastAmount = userRegistrationDate?.let {
            (todayMillis - userRegistrationDate) / DAY_MILLIS
        } ?: 0L

        items
            .expand(
                direction = DateDirection.Future,
                amount = 50
            )
            .expand(
                direction = DateDirection.Past,
                amount = pastAmount.toInt()
            )
    }

    override fun appendItems(
        currentItems: List<DateItem>,
        direction: DateDirection,
        amount: Int
    ): Flow<ResultState<List<DateItem>>> = flowLocalRequest {
        currentItems.expand(direction, amount)
    }

    override fun getDateItems(startOfDay: Long): Flow<ResultState<List<UserAction>>> = flowRequest(
        mapper = { map { it.asExternalModel() } },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.fetchDateItems(token, startOfDay)
        }
    )

    private fun List<DateItem>.expand(
        direction: DateDirection,
        amount: Int
    ): List<DateItem> {
        val items = this.toMutableList()

        repeat(amount) {
            when (direction) {
                DateDirection.Future -> {
                    val lastItem = items.last()
                    items.add(lastItem.addDay())
                }

                DateDirection.Past -> {
                    val firstItem = items.first()
                    items.add(0, firstItem.removeDay())
                }
            }
        }

        return items.toList()
    }

    override suspend fun saveUpdatedNoteId(noteId: Int) {
        localDataSource.edit { preferences ->
            preferences[CALENDARS_UPDATED_NOTE_ID] = noteId
        }
    }

    override suspend fun getUpdatedNoteId(): Flow<ResultState<Int>> = flowLocalRequest {
        val noteId = localDataSource.data.first()[CALENDARS_UPDATED_NOTE_ID]
        localDataSource.edit { preferences ->
            preferences.remove(CALENDARS_UPDATED_NOTE_ID)
        }

        noteId!!
    }
}