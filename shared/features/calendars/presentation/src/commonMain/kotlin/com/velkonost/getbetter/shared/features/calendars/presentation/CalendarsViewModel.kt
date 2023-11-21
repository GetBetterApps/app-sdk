package com.velkonost.getbetter.shared.features.calendars.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.user.UserAction
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
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.ActionUIItem
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsAction
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsNavigation
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsViewState
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.DateUIItem
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.SelectedDate
import com.velkonost.getbetter.shared.features.calendars.presentation.model.UserActionType
import com.velkonost.getbetter.shared.features.calendars.presentation.model.type
import com.velkonost.getbetter.shared.features.comments.api.CommentsRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow

class CalendarsViewModel
internal constructor(
    private val calendarsRepository: CalendarsRepository,
    private val notesRepository: NotesRepository,
    private val areasRepository: AreasRepository,
    private val commentsRepository: CommentsRepository,

    ) : BaseViewModel<CalendarsViewState, CalendarsAction, CalendarsNavigation, Nothing>(
    initialState = CalendarsViewState()
) {

    private var _dates: MutableStateFlow<List<DateItem>> = MutableStateFlow(emptyList())
    private var _datesData: MutableMap<Long, List<ActionUIItem<*>>> = mutableMapOf()

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

    private fun getItemsForDay(value: Long) {
        launchJob {
            calendarsRepository.getDateItems(value) collectAndProcess {
                isLoading {
                    val selectedDate = viewState.value.datesState.selectedDate?.copy(
                        isLoading = it
                    )
                    val datesState = viewState.value.datesState.copy(selectedDate = selectedDate)
                    emit(viewState.value.copy(datesState = datesState))
                }
                onSuccess { list ->
                    val selectedDateItems = mutableListOf<ActionUIItem<*>>()
                    list?.let {
                        list.forEach { item ->
                            when (item.type) {
                                is UserActionType.UserRegistered -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Long>(
                                            dayId = value,
                                            id = item.datetime,
                                            description = item.datetimeStr
                                        )
                                    )
                                }

                                is UserActionType.UserCreatedArea -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Area>(
                                            dayId = value,
                                            id = item.datetime,
                                        )
                                    )
                                    getAreaForAction(
                                        dayId = value,
                                        actionId = item.datetime,
                                        areaId = item.entityId.toInt()
                                    )
                                }

                                else -> {

                                }
                            }
                        }

                        _datesData[value] = selectedDateItems

                    }
                }
            }
        }
    }

    private fun getAreaForAction(
        dayId: Long,
        actionId: Long,
        areaId: Int
    ) {
        launchJob {
            areasRepository.fetchAreaDetails(areaId) collectAndProcess {
                isLoading {
                    val items = viewState.value.datesState.selectedDate?.items?.toMutableList()
                    val currentItem = items?.firstOrNull { item -> item.id == actionId }
                    val indexOfCurrentItem =
                        viewState.value.datesState.selectedDate?.items?.indexOfFirst { item ->
                            item.id == actionId
                        }

                    if (
                        items != null && currentItem != null
                        && indexOfCurrentItem != null && indexOfCurrentItem != -1
                    ) {
                        items[indexOfCurrentItem] = currentItem.copy(isLoading = it)

                        if (viewState.value.datesState.selectedDate?.id == dayId) {
                            val selectedDate = viewState.value.datesState.selectedDate?.copy(
                                items = items
                            )
                            val datesState = viewState.value.datesState.copy(
                                selectedDate = selectedDate
                            )
                            emit(viewState.value.copy(datesState = datesState))
                        }
                    }
                }
                onSuccess { item ->
                    item?.let {
                        val items = _datesData[dayId]?.toMutableList()
                        val currentItem = items
                            ?.firstOrNull { item -> item.id == actionId && item.data is Area }
                            .takeIf { it?.data is Area }
                        val indexOfCurrentItem =
                            viewState.value.datesState.selectedDate?.items?.indexOfFirst { item ->
                                item.id == actionId
                            }

                        if (
                            items != null && currentItem != null
                            && indexOfCurrentItem != null && indexOfCurrentItem != -1
                        ) {
                            items[indexOfCurrentItem] = currentItem.copy(data = item)

                            if (viewState.value.datesState.selectedDate?.id == dayId) {
                                val selectedDate = viewState.value.datesState.selectedDate?.copy(
                                    items = items
                                )
                                val datesState = viewState.value.datesState.copy(
                                    selectedDate = selectedDate
                                )
                                emit(viewState.value.copy(datesState = datesState))
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getUserActionDetails(value: UserAction) {
        launchJob {
            when (value.type) {
                is UserActionType.UserRegistered -> {

                }

                else -> {

                }
            }
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