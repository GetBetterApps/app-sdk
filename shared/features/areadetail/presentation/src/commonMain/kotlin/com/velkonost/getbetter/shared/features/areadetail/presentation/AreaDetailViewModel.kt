package com.velkonost.getbetter.shared.features.areadetail.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.hint.UIHint
import com.velkonost.getbetter.shared.core.model.likes.LikeType
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.areadetail.api.AreaDetailRepository
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailAction
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailEvent
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailNavigation
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailViewState
import com.velkonost.getbetter.shared.features.areadetail.presentation.model.toUI
import com.velkonost.getbetter.shared.features.likes.api.LikesRepository
import kotlinx.coroutines.Job

class AreaDetailViewModel
internal constructor(
    private val areasRepository: AreasRepository,
    private val likesRepository: LikesRepository,
    private val areaDetailRepository: AreaDetailRepository
) : BaseViewModel<AreaDetailViewState, AreaDetailAction, AreaDetailNavigation, AreaDetailEvent>(
    initialState = AreaDetailViewState()
) {

    private var likesJob: Job? = null

    override fun dispatch(action: AreaDetailAction) = when (action) {
        is AreaDetailAction.Load -> fetchArea(action.areaId)
        is AreaDetailAction.EmojiChanged -> obtainEmojiChanged(action.value)
        is AreaDetailAction.NameChanged -> obtainNameChanged(action.value)
        is AreaDetailAction.DescriptionChanged -> obtainDescriptionChanged(action.value)
        is AreaDetailAction.StartEdit -> obtainStartEdit()
        is AreaDetailAction.EndEdit -> obtainEndEdit()
        is AreaDetailAction.DeleteClick -> obtainDeleteArea()
        is AreaDetailAction.LeaveClick -> obtainLeaveArea()
        is AreaDetailAction.JoinClick -> obtainJoinArea()
        is AreaDetailAction.CancelEdit -> obtainCancelEdit()
        is AreaDetailAction.LikeClick -> obtainLikeClick()
        is AreaDetailAction.HintClick -> showHint()
    }

    private fun showHint(firstTime: Boolean = false) {
        if (firstTime) {
            launchJob {
                if (areaDetailRepository.shouldShowHint()) {
                    UIHint.DiaryAreaDetail.send(isMain = false)
                }
            }
        } else UIHint.DiaryAreaDetail.send(isMain = false)
    }

    private fun obtainLikeClick() {
        if (likesJob?.isActive == true) return

        launchJob {
            val likeType = when (viewState.value.initialItem!!.likesData.userLike) {
                LikeType.Positive -> LikeType.None
                else -> LikeType.Positive
            }
            likesRepository.addLike(
                entityType = EntityType.Area,
                entityId = viewState.value.initialItem!!.id,
                likeType = likeType
            ) collectAndProcess {
                isLoading {
                    val itemLikesData =
                        viewState.value.initialItem!!.likesData.copy(isLikesLoading = true)
                    val initialItem = viewState.value.initialItem!!.copy(likesData = itemLikesData)
                    val modifierItem =
                        viewState.value.modifiedItem!!.copy(likesData = itemLikesData)
                    emit(
                        viewState.value.copy(
                            initialItem = initialItem,
                            modifiedItem = modifierItem
                        )
                    )
                }
                onSuccess { entityLikes ->
                    entityLikes?.let {
                        val itemLikesData = LikesData(
                            totalLikes = it.total,
                            userLike = it.userLikeType
                        )
                        val initialItem =
                            viewState.value.initialItem!!.copy(likesData = itemLikesData)
                        val modifierItem =
                            viewState.value.modifiedItem!!.copy(likesData = itemLikesData)
                        emit(
                            viewState.value.copy(
                                initialItem = initialItem,
                                modifiedItem = modifierItem
                            )
                        )
                    }
                }
            }
        }
    }

    private fun fetchArea(areaId: Int) {
        launchJob {
            areasRepository.fetchAreaDetails(areaId) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    it?.let { area ->
                        emit(
                            viewState.value.copy(
                                initialItem = area.toUI(),
                                modifiedItem = area.toUI(),
                                isAllowJoin = area.isAllowJoin,
                                isAllowDelete = area.isAllowDelete,
                                isAllowEdit = area.isAllowEdit,
                                isAllowLeave = area.isAllowLeave
                            )
                        )

                        showHint(firstTime = true)
                    }
                }
            }
        }
    }

    private fun obtainEmojiChanged(value: Emoji) {
        val item = viewState.value.modifiedItem
        item?.let {
            emit(viewState.value.copy(modifiedItem = it.copy(emoji = value)))
        }
    }

    private fun obtainNameChanged(value: String) {
        val item = viewState.value.modifiedItem
        item?.let {
            emit(viewState.value.copy(modifiedItem = it.copy(name = value)))
        }
    }

    private fun obtainDescriptionChanged(value: String) {
        val item = viewState.value.modifiedItem
        item?.let {
            emit(viewState.value.copy(modifiedItem = it.copy(description = value)))
        }
    }

    private fun obtainStartEdit() {
        emit(viewState.value.copy(isEditing = true))
    }

    private fun obtainEndEdit() {
        emit(viewState.value.copy(isEditing = false))

        checkNotNull(viewState.value.modifiedItem) { return }

        launchJob {
            val area = viewState.value.modifiedItem!!
            areasRepository.editArea(
                id = area.id,
                name = area.name,
                description = area.description,
                emojiId = area.emoji.id
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { area ->
                    area?.let {
                        emit(AreaDetailEvent.EditSuccess(area.id))
                    }
                }
                onFailureWithMsg { _, _ ->
                    val initialItem = viewState.value.initialItem
                    emit(
                        viewState.value.copy(
                            isEditing = false,
                            modifiedItem = initialItem?.copy()
                        )
                    )
                }
            }
        }
    }

    private fun obtainDeleteArea() {
        checkNotNull(viewState.value.modifiedItem) { return }

        launchJob {
            areasRepository.deleteArea(viewState.value.modifiedItem!!.id) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { area ->
                    area?.let { emit(AreaDetailEvent.DeleteSuccess(it.id)) }
                }
            }
        }
    }

    private fun obtainLeaveArea() {
        checkNotNull(viewState.value.modifiedItem) { return }

        launchJob {
            areasRepository.leaveArea(viewState.value.modifiedItem!!.id) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { area ->
                    area?.let {
                        emit(
                            viewState.value.copy(
                                isAllowJoin = true,
                                isAllowLeave = false
                            )
                        )
                        emit(AreaDetailEvent.LeaveSuccess(area.id))
                    }

                }
            }
        }
    }

    private fun obtainCancelEdit() {
        val initialItem = viewState.value.initialItem

        checkNotNull(initialItem) { return }

        emit(
            viewState.value.copy(
                isEditing = false,
                modifiedItem = initialItem.copy()
            )
        )
    }

    private fun obtainJoinArea() {
        val item = viewState.value.initialItem

        checkNotNull(item) { return }

        launchJob {
            areasRepository.addUserArea(item.id) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { area ->
                    area?.let {
                        emit(
                            viewState.value.copy(
                                isAllowJoin = false,
                                isAllowLeave = true
                            )
                        )
                        emit(AreaDetailEvent.JoinSuccess(area.id))
                    }
                }
            }
        }
    }
}