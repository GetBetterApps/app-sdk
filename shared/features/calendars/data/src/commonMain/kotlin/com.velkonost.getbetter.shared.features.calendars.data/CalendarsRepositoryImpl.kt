package com.velkonost.getbetter.shared.features.calendars.data

import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.calendars.api.CalendarsRepository
import com.velkonost.getbetter.shared.features.calendars.api.model.DateDirection
import com.velkonost.getbetter.shared.features.calendars.api.model.DateItem
import kotlinx.coroutines.flow.Flow

class CalendarsRepositoryImpl : CalendarsRepository {

    override fun getInitItems(): Flow<ResultState<List<DateItem>>> {
        TODO("Not yet implemented")
    }

    override fun appendItems(
        currentItems: List<DateItem>,
        direction: DateDirection,
        amount: Int
    ): Flow<ResultState<List<DateItem>>> {
        TODO("Not yet implemented")
    }
}