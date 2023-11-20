package com.velkonost.getbetter.shared.features.calendars.presentation

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToShortDateWithoutRelation
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.calendars.api.CalendarsRepository
import com.velkonost.getbetter.shared.features.calendars.api.model.DateDirection
import com.velkonost.getbetter.shared.features.calendars.api.model.DateItem
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsAction
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsNavigation
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsViewState
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.DateUIItem
import kotlinx.coroutines.flow.flow

class CalendarsViewModel
internal constructor(
    private val calendarsRepository: CalendarsRepository
) : BaseViewModel<CalendarsViewState, CalendarsAction, CalendarsNavigation, Nothing>(
    initialState = CalendarsViewState()
) {

    private var _dates: List<DateItem> = emptyList()

    init {
        initItems()

        launchJob {
            flow<List<DateItem>> { _dates }.collect { dates ->
                val datesState = viewState.value.datesState.copy(
                    items = dates.map {
                        DateUIItem(
                            date = it.millis.convertToShortDateWithoutRelation()
                        )
                    }
                )
                emit(viewState.value.copy(datesState = datesState))
            }
        }
    }

    override fun dispatch(action: CalendarsAction) = when (action) {
        is CalendarsAction.LoadMoreNextDates -> obtainLoadMore(DateDirection.Future)
        is CalendarsAction.LoadMorePreviousDates -> obtainLoadMore(DateDirection.Past)
    }

    private fun initItems() {
        launchJob {
            calendarsRepository.getInitItems() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { list ->
                    list?.let { _dates = it }
                }
            }
        }
    }

    private fun obtainLoadMore(direction: DateDirection) {
        launchJob {
            calendarsRepository.appendItems(
                currentItems = _dates,
                direction = direction,
                amount = 10
            ) collectAndProcess {
                isLoading {
                    val datesState = if (direction == DateDirection.Future) {
                        viewState.value.datesState.copy(isNextLoading = it)
                    } else viewState.value.datesState.copy(isPreviousLoading = it)
                    emit(viewState.value.copy(datesState = datesState))
                }
                onSuccess { list ->
                    list?.let { _dates = it }
                }
            }
        }
    }

}