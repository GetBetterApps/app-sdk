package com.velkonost.getbetter.shared.features.calendars.presentation

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToDay
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToDayOfWeek
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToMonthDay
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToYear
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
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.SelectedDate
import kotlinx.coroutines.flow.MutableStateFlow

class CalendarsViewModel
internal constructor(
    private val calendarsRepository: CalendarsRepository
) : BaseViewModel<CalendarsViewState, CalendarsAction, CalendarsNavigation, Nothing>(
    initialState = CalendarsViewState()
) {

    private var _dates: MutableStateFlow<List<DateItem>> = MutableStateFlow(emptyList())

    init {
        initItems()

        launchJob {
            _dates.collect { dates ->
                var datesState = viewState.value.datesState.copy(
                    items = dates.map {
                        DateUIItem(
                            id = it.millis,
                            day = it.millis.convertToDay(),
                            dayOfWeek = it.millis.convertToDayOfWeek()
                        )
                    }
                )

                if (datesState.selectedDate == null) {
                    val dateMillis = dates.first { it.selectedByDefault }.millis
                    val selectedDate = SelectedDate(
                        id = dateMillis,
                        year = dateMillis.convertToYear(),
                        monthDay = dateMillis.convertToMonthDay()
                    )
                    datesState = datesState.copy(
                        selectedDate = selectedDate,
                        isNextLoading = false,
                        isPreviousLoading = false
                    )
                }

                emit(viewState.value.copy(datesState = datesState))
            }
        }
    }

    override fun dispatch(action: CalendarsAction) = when (action) {
        is CalendarsAction.LoadMoreNextDates -> obtainLoadMore(DateDirection.Future)
        is CalendarsAction.DateClick -> obtainDateClick(action.id)
    }

    private fun initItems() {
        launchJob {
            calendarsRepository.getInitItems() collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { list ->
                    list?.let {
                        _dates.value = it
                    }
                }
            }
        }
    }

    private fun obtainDateClick(dateId: Long) {
        viewState.value.datesState.items.firstOrNull { it.id == dateId }?.let {
            val selectedDate = SelectedDate(
                id = dateId,
                year = dateId.convertToYear(),
                monthDay = dateId.convertToMonthDay()
            )
            val datesState = viewState.value.datesState.copy(selectedDate = selectedDate)
            emit(viewState.value.copy(datesState = datesState))
        }
    }

    private fun obtainLoadMore(direction: DateDirection) {
        launchJob {
            calendarsRepository.appendItems(
                currentItems = _dates.value,
                direction = direction,
                amount = 50
            ) collectAndProcess {
                isLoading {
                    val datesState = if (direction == DateDirection.Future) {
                        viewState.value.datesState.copy(isNextLoading = it)
                    } else viewState.value.datesState.copy(isPreviousLoading = it)
                    emit(viewState.value.copy(datesState = datesState))
                }
                onSuccess { list ->
                    list?.let { _dates.value = it }
                }
            }
        }
    }

}