package com.velkonost.getbetter.shared.features.calendars.presentation

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.user.ActionType
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
import kotlinx.coroutines.flow.MutableStateFlow

class CalendarsViewModel
internal constructor(
    private val calendarsRepository: CalendarsRepository
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
                            if (item.entityType == EntityType.User && item.actionType == ActionType.Add) {
//                                user registered
//                                val actionUIItem = ActionUIItem<Long>(
//                                    type = EntityType.User,
//                                    isLoading = false,
//                                    data = null
//                                )
//                                selectedDateItems.add(actionUIItem)
                            } else if (item.entityType == EntityType.Area && item.actionType == ActionType.Add) {
                                // area created
//                                val actionUIItem = ActionUIItem<Area>(
//                                    type = EntityType.Area,
//                                )
                            } else if (item.entityType == EntityType.Area && item.actionType == ActionType.Join) {

                            } else if (item.entityType == EntityType.Area && item.actionType == ActionType.Leave) {

                            } else if (item.entityType == EntityType.Note && item.actionType == ActionType.Add) {

                            } else if (item.entityType == EntityType.Note && item.actionType == ActionType.Complete) {

                            } else if (item.entityType == EntityType.SubGoal && item.actionType == ActionType.Complete) {

                            } else if (item.entityType == EntityType.Comment && item.actionType == ActionType.Add) {
                                // i created comment
                            } else if (item.entityType == EntityType.Comment && item.actionType == ActionType.AddInbox) {
                                //smbd created comment for my entity
                            } else if (item.entityType == EntityType.Like && item.actionType == ActionType.Add) {

                            } else if (item.entityType == EntityType.Like && item.actionType == ActionType.AddInbox) {

                            } else if (item.entityType == EntityType.Follow && item.actionType == ActionType.Add) {
                                // i followed to smbd
                            } else if (item.entityType == EntityType.Follower && item.actionType == ActionType.Add) {
                                //smbd followed to me
                            }
                        }
                    }
                }
            }
        }
    }

    private fun getUserActionDetails(value: UserAction) {

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