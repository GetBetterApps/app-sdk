package com.velkonost.getbetter.shared.features.calendars.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
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
import com.velkonost.getbetter.shared.features.userinfo.api.UserInfoRepository
import kotlinx.coroutines.flow.MutableStateFlow

class CalendarsViewModel
internal constructor(
    private val calendarsRepository: CalendarsRepository,
    private val notesRepository: NotesRepository,
    private val areasRepository: AreasRepository,
    private val commentsRepository: CommentsRepository,
    private val userInfoRepository: UserInfoRepository
) : BaseViewModel<CalendarsViewState, CalendarsAction, CalendarsNavigation, Nothing>(
    initialState = CalendarsViewState()
) {

    private var _dates: MutableStateFlow<List<DateItem>> = MutableStateFlow(emptyList())
    private var _datesData: MutableMap<Long, List<ActionUIItem<*, *>>> = mutableMapOf()

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
                    val selectedDateItems = mutableListOf<ActionUIItem<*, *>>()
                    list?.let {
                        list.forEach { item ->
                            when (item.type) {
                                is UserActionType.UserRegistered -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Long, Nothing>(
                                            dayId = value,
                                            id = item.datetime,
                                            description = item.datetimeStr
                                        )
                                    )
                                }

                                is UserActionType.UserCreatedArea,
                                is UserActionType.UserJoinedArea,
                                is UserActionType.UserLeavedArea -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Area, Nothing>(
                                            dayId = value,
                                            id = item.datetime,
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.Area,
                                    )
                                }

                                is UserActionType.UserCreatedNote,
                                is UserActionType.UserCompletedGoal -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Note, Nothing>(
                                            dayId = value,
                                            id = item.datetime
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.Note,
                                    )
                                }

                                is UserActionType.UserCompletedSubGoal -> {
                                    // ???
                                }

                                is UserActionType.UserCreatedComment,
                                is UserActionType.UserGotComment -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Comment, Nothing>(
                                            dayId = value,
                                            id = item.datetime
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.Comment,
                                    )

                                    // user got comment - fetch user info
                                }

                                is UserActionType.UserCreatedLike,
                                is UserActionType.UserGotLike -> {
                                    // fetch entity(area or note)
                                    // fetch userinfo on gotlike
                                }

                                is UserActionType.UserFollowed,
                                is UserActionType.UserGotFollower -> {
                                    // fetch info about another user
                                    selectedDateItems.add(
                                        ActionUIItem<UserInfoShort, Nothing>(
                                            dayId = value,
                                            id = item.datetime
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.User,
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

    private fun getActionDetails(
        dayId: Long,
        actionId: Long,
        entityId: String,
        entityType: EntityType
    ) {
        launchJob {
            val request = when (entityType) {
                EntityType.Area -> areasRepository.fetchAreaDetails(entityId.toInt())
                EntityType.Note -> notesRepository.getNoteDetails(entityId.toInt())
                EntityType.Comment -> commentsRepository.getComment(entityId.toInt())
                EntityType.User -> userInfoRepository.fetchInfoAboutOtherUser(entityId)
                else -> return@launchJob
            }

            request collectAndProcess {
                isLoading {
                    updateItemLoadingState(dayId, actionId, it)
                }
                onSuccess { item ->
                    item?.let { updateItemData(dayId, actionId, it) }
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

    private fun updateItemLoadingState(dayId: Long, itemId: Long, isLoading: Boolean) {
        val items = _datesData[dayId]?.toMutableList()
        val currentItem = items?.firstOrNull { item -> item.id == itemId }
        val indexOfCurrentItem = items?.indexOfFirst { item ->
            item.id == itemId
        }

        if (
            items != null && currentItem != null
            && indexOfCurrentItem != null && indexOfCurrentItem != -1
        ) {
            items[indexOfCurrentItem] = currentItem.copy(isLoading = isLoading)

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

    private inline fun <reified T> updateItemData(dayId: Long, actionId: Long, data: T) {
        val items = _datesData[dayId]?.toMutableList()
        val currentItem = items
            ?.firstOrNull { item -> item.id == actionId }
        val indexOfCurrentItem = items?.indexOfFirst { item ->
            item.id == actionId
        }

        if (
            items != null && currentItem != null
            && indexOfCurrentItem != null && indexOfCurrentItem != -1
        ) {
            items[indexOfCurrentItem] = currentItem.copy(data = data)

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