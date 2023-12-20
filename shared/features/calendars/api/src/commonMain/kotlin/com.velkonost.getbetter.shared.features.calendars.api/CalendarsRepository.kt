package com.velkonost.getbetter.shared.features.calendars.api

import com.velkonost.getbetter.shared.core.model.user.UserAction
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.calendars.api.model.DateDirection
import com.velkonost.getbetter.shared.features.calendars.api.model.DateItem
import kotlinx.coroutines.flow.Flow

interface CalendarsRepository {

    fun getInitItems(): Flow<ResultState<List<DateItem>>>

    fun appendItems(
        currentItems: List<DateItem>,
        direction: DateDirection,
        amount: Int
    ): Flow<ResultState<List<DateItem>>>

    fun getDateItems(startOfDay: Long): Flow<ResultState<List<UserAction>>>

    suspend fun saveUpdatedNoteId(noteId: Int)

    suspend fun getUpdatedNoteId(): Flow<ResultState<Int>>

    suspend fun shouldShowHint(): Boolean

}