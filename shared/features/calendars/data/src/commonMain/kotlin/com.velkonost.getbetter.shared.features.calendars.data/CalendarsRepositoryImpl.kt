package com.velkonost.getbetter.shared.features.calendars.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.USER_REGISTRATION_MILLIS
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.DAY_MILLIS
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.todayMillis
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowLocalRequest
import com.velkonost.getbetter.shared.features.calendars.api.CalendarsRepository
import com.velkonost.getbetter.shared.features.calendars.api.model.DateDirection
import com.velkonost.getbetter.shared.features.calendars.api.model.DateItem
import com.velkonost.getbetter.shared.features.calendars.api.model.addDay
import com.velkonost.getbetter.shared.features.calendars.api.model.removeDay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class CalendarsRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
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
}