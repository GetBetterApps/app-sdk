package com.velkonost.getbetter.shared.features.calendars.data

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.todayMillis
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowLocalRequest
import com.velkonost.getbetter.shared.features.calendars.api.CalendarsRepository
import com.velkonost.getbetter.shared.features.calendars.api.model.DateDirection
import com.velkonost.getbetter.shared.features.calendars.api.model.DateItem
import com.velkonost.getbetter.shared.features.calendars.api.model.addDay
import com.velkonost.getbetter.shared.features.calendars.api.model.removeDay
import kotlinx.coroutines.flow.Flow

class CalendarsRepositoryImpl : CalendarsRepository {

    override fun getInitItems(): Flow<ResultState<List<DateItem>>> = flowLocalRequest {
        val todayDate = DateItem(
            millis = todayMillis
        )

        val items = listOf(todayDate)

        items
            .expand(
                direction = DateDirection.Future,
                amount = 10
            )
            .expand(
                direction = DateDirection.Past,
                amount = 10
            )
    }

    override fun appendItems(
        currentItems: List<DateItem>,
        direction: DateDirection,
        amount: Int
    ): Flow<ResultState<List<DateItem>>> = flowLocalRequest {
        currentItems.expand(direction, amount)
    }

    private fun List<DateItem>.expand(direction: DateDirection, amount: Int): List<DateItem> {
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