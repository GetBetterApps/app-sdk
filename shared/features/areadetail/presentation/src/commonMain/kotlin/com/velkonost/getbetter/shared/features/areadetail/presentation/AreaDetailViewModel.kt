package com.velkonost.getbetter.shared.features.areadetail.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailAction
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailEvent
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailNavigation
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailViewState
import com.velkonost.getbetter.shared.features.areadetail.presentation.model.toUI

class AreaDetailViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<AreaDetailViewState, AreaDetailAction, AreaDetailNavigation, AreaDetailEvent>(
    initialState = AreaDetailViewState()
) {

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
    }

    private fun fetchArea(areaId: Int) {
        launchJob {
            areasRepository.fetchAreaDetails(areaId)
                .collect { result ->
                    with(result) {
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
                            }

                        }

                        onFailureWithMsg { _, message ->
                            message?.let { emit(it) }
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
            ).collect { result ->
                with(result) {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }

                    onSuccess { area ->
                        area?.let {
                            emit(AreaDetailEvent.EditSuccess(area.id))
                        }
                    }

                    onFailureWithMsg { _, message ->
                        message?.let { emit(it) }
                    }
                }
            }
        }
    }

    private fun obtainDeleteArea() {
        checkNotNull(viewState.value.modifiedItem) { return }

        launchJob {
            areasRepository.deleteArea(viewState.value.modifiedItem!!.id)
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }
                        onSuccess { area ->
                            area?.let { emit(AreaDetailEvent.DeleteSuccess(it.id)) }
                        }
                    }
                }
        }
    }

    private fun obtainLeaveArea() {
        checkNotNull(viewState.value.modifiedItem) {
            return
        }

        launchJob {
            areasRepository.leaveArea(viewState.value.modifiedItem!!.id)
                .collect { result ->
                    with(result) {
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
    }

    private fun obtainCancelEdit() {
        val initialItem = viewState.value.initialItem

        checkNotNull(initialItem) {
            return
        }

        emit(
            viewState.value.copy(
                isEditing = false,
                modifiedItem = initialItem.copy()
            )
        )
    }

    private fun obtainJoinArea() {
        val item = viewState.value.initialItem

        checkNotNull(item) {
            return
        }

        launchJob {
            areasRepository.addUserArea(item.id)
                .collect { result ->
                    with(result) {
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

}