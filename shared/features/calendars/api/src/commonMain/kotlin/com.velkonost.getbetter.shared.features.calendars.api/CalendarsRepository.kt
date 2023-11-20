package com.velkonost.getbetter.shared.features.calendars.api

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

}