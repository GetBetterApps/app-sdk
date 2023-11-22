package com.velkonost.getbetter.shared.features.calendars.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.SubNote
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
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.time.Duration.Companion.seconds

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
    private var _datesData: MutableStateFlow<MutableMap<Long, List<ActionUIItem<*, *>>>> =
        MutableStateFlow(mutableMapOf())

    private var fetchDateItemsJob: Job? = null

    init {
        initItems()

        launchJob {
            _dates
                .collect { dates ->
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

                    getItemsForDay(dateMillis)
                }

                emit(viewState.value.copy(datesState = datesState))
            }
        }

        launchJob {
            _datesData.collect { data ->
                val selectedDate = viewState.value.datesState.selectedDate
                selectedDate?.let {
                    if (data.containsKey(selectedDate.id)) {
                        val items = data[selectedDate.id]!!
                        selectedDate.items = items

                        val datesState = viewState.value.datesState.copy(selectedDate = it)
                        emit(viewState.value.copy(datesState = datesState))
                    }
                }
            }
        }
    }

    override fun dispatch(action: CalendarsAction) = when (action) {
        is CalendarsAction.LoadMoreNextDates -> obtainLoadMore(DateDirection.Future)
        is CalendarsAction.DateClick -> obtainDateClick(action.id)
        is CalendarsAction.NoteClick -> obtainNoteClick(action.value)
    }

    private fun obtainNoteClick(value: Note) {
        launchJob {
            calendarsRepository.saveUpdatedNoteId(value.id)
            emit(CalendarsNavigation.NavigateToNoteDetail(value))
        }
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

            launchJob { getItemsForDay(dateId) }

            val datesState = viewState.value.datesState.copy(selectedDate = selectedDate)
            emit(viewState.value.copy(datesState = datesState))
        }
    }

    private fun getItemsForDay(value: Long) {
        fetchDateItemsJob?.cancel()
        fetchDateItemsJob = launchJob {
            delay(1.seconds)

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
                                            description = StringDesc.Resource(SharedR.strings.action_user_registered)
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
                                            description = StringDesc.Resource(
                                                when (item.type) {
                                                    is UserActionType.UserCreatedArea -> SharedR.strings.action_user_created_area
                                                    is UserActionType.UserJoinedArea -> SharedR.strings.action_user_joined_area
                                                    else -> SharedR.strings.action_user_leaved_area
                                                }
                                            )
                                        )
                                    )

                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.Area,
                                        type = item.type!!
                                    )
                                }

                                is UserActionType.UserCreatedNote,
                                is UserActionType.UserCompletedGoal -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Note, Nothing>(
                                            dayId = value,
                                            id = item.datetime,
                                            description = StringDesc.Resource(
                                                when (item.type) {
                                                    is UserActionType.UserCreatedNote -> SharedR.strings.action_user_created_note
                                                    else -> SharedR.strings.action_user_completed_goal
                                                }
                                            )
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.Note,
                                        type = item.type!!
                                    )
                                }

                                is UserActionType.UserCompletedSubGoal -> {
                                    selectedDateItems.add(
                                        ActionUIItem<SubNote, Note>(
                                            dayId = value,
                                            id = item.datetime,
                                            description = StringDesc.Resource(SharedR.strings.action_user_completed_subgoal)
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        type = item.type!!,
                                        actionId = item.datetime,
                                        entityType = EntityType.SubGoal,
                                        entityId = item.entityId,
                                        parentEntityId = item.parentId,
                                        parentEntityType = item.parentEntityType!!
                                    )
                                }

                                is UserActionType.UserCreatedComment,
                                is UserActionType.UserGotComment -> {
                                    selectedDateItems.add(
                                        ActionUIItem<Comment, Nothing>(
                                            dayId = value,
                                            id = item.datetime,
                                            description = when (item.type) {
                                                is UserActionType.UserCreatedComment -> StringDesc.Resource(
                                                    SharedR.strings.action_user_created_comment
                                                )

                                                else -> null
                                            }
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.Comment,
                                        parentEntityId = item.parentId,
                                        parentEntityType = item.parentEntityType,
                                        type = item.type!!
                                    )
                                }

                                is UserActionType.UserCreatedLike,
                                is UserActionType.UserGotLike -> {

                                    selectedDateItems.add(
                                        if (item.parentEntityType == EntityType.Area) {
                                            ActionUIItem<Area, UserInfoShort>(
                                                dayId = value,
                                                id = item.datetime,
                                                description = StringDesc.Resource(SharedR.strings.action_user_created_like)
                                            )
                                        } else {
                                            ActionUIItem<Note, UserInfoShort>(
                                                dayId = value,
                                                id = item.datetime,
                                                description = StringDesc.Resource(
                                                    when (item.type) {
                                                        is UserActionType.UserCreatedLike -> SharedR.strings.action_user_created_like
                                                        else -> SharedR.strings.action_user_got_like_anonymous
                                                    }
                                                )
                                            )
                                        }
                                    )

                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = item.entityType,
                                        type = item.type!!,
                                        parentEntityId = item.parentId,
                                        parentEntityType = item.parentEntityType
                                    )
                                }

                                is UserActionType.UserFollowed,
                                is UserActionType.UserGotFollower -> {
                                    // fetch info about another user
                                    selectedDateItems.add(
                                        ActionUIItem<UserInfoShort, Nothing>(
                                            dayId = value,
                                            id = item.datetime,
                                            description = StringDesc.Resource(SharedR.strings.action_user_followed)
                                        )
                                    )
                                    getActionDetails(
                                        dayId = value,
                                        actionId = item.datetime,
                                        entityId = item.entityId,
                                        entityType = EntityType.User,
                                        type = item.type!!
                                    )

                                }

                                else -> {

                                }
                            }
                        }

                        _datesData.value[value] = selectedDateItems

                    }
                }
            }
        }
    }

    private fun getActionDetails(
        dayId: Long,
        actionId: Long,
        entityId: String,
        entityType: EntityType,
        parentEntityId: String? = null,
        parentEntityType: EntityType? = null,
        type: UserActionType
    ) {
        launchJob {
            parentEntityId?.let {
                val parentRequest = when (parentEntityType) {
                    EntityType.Area -> areasRepository.fetchAreaDetails(parentEntityId.toInt())
                    EntityType.Note -> notesRepository.getNoteDetails(parentEntityId.toInt())
                    EntityType.Comment -> commentsRepository.getComment(parentEntityId.toInt())
                    EntityType.User -> userInfoRepository.fetchInfoAboutOtherUser(parentEntityId)
                    else -> return@launchJob
                }
                parentRequest collectAndProcess {
                    isLoading {
                        updateItemLoadingState(dayId, actionId, it)
                    }
                    onSuccess { item ->
                        item?.let {
                            if (type == UserActionType.UserCompletedSubGoal && item is Note) {
                                updateItemData(
                                    dayId,
                                    actionId,
                                    type = type,
                                    data = item.subNotes.first { it.id == entityId.toInt() },
                                    relatedData = item
                                )
                            } else {
                                updateItemData<Any, Any>(
                                    dayId,
                                    actionId,
                                    type = type,
                                    relatedData = it
                                )
                            }
                        }
                    }
                }
            }

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
                    item?.let {
                        updateItemData<Any, Any>(dayId, actionId, it, type = type)
                    }
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
        val items = _datesData.value[dayId]?.toMutableList()
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

    private inline fun <reified T, reified S> updateItemData(
        dayId: Long,
        actionId: Long,
        data: T? = null,
        relatedData: S? = null,
        type: UserActionType
    ) {
        val items = _datesData.value[dayId]?.toMutableList()
        val currentItem = items?.firstOrNull { item -> item.id == actionId }
        val indexOfCurrentItem = items?.indexOfFirst { item ->
            item.id == actionId
        }

        if (
            items != null && currentItem != null
            && indexOfCurrentItem != null && indexOfCurrentItem != -1
        ) {

            val itemData = currentItem.data
            val itemRelatedData = currentItem.relatedData

            items[indexOfCurrentItem] = currentItem.copy(
                data = data ?: itemData,
                relatedData = relatedData ?: itemRelatedData
            )

            if (type == UserActionType.UserGotComment && data is Comment) {
                items[indexOfCurrentItem].description = StringDesc.ResourceFormatted(
                    SharedR.strings.action_user_got_comment, data.author.displayName ?: ""
                )
            }

            if (type == UserActionType.UserGotFollower && data is UserInfoShort) {
                items[indexOfCurrentItem].description = StringDesc.ResourceFormatted(
                    SharedR.strings.action_user_got_follower, data.displayName ?: ""
                )
            }

            _datesData.value[dayId] = items

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