package com.velkonost.getbetter.shared.features.areadetail.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onFailure
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailAction
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailEvent
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailNavigation
import com.velkonost.getbetter.shared.features.areadetail.presentation.contract.AreaDetailViewState
import com.velkonost.getbetter.shared.features.areadetail.presentation.model.toUI
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import model.getUserTermsOfMembership

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
    }

    private fun fetchArea(areaId: String) {
        launchJob {
            areasRepository.fetchAreaDetails(areaId)
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }

                        onSuccess {
                            it?.let { area ->
                                val userId = Firebase.auth.currentUser!!.uid
                                val areaTermsOfMembership = area.getUserTermsOfMembership(userId)

                                emit(
                                    viewState.value.copy(
                                        item = area.toUI(areaTermsOfMembership)
                                    )
                                )
                            }

                        }

                        onFailure {

                        }
                    }

                }
        }
    }

    private fun obtainEmojiChanged(value: Emoji) {
        val item = viewState.value.item
        item?.let {
            emit(viewState.value.copy(item = it.copy(emoji = value)))
        }
    }

    private fun obtainNameChanged(value: String) {
        val item = viewState.value.item
        item?.let {
            emit(viewState.value.copy(item = it.copy(name = value)))
        }
    }

    private fun obtainDescriptionChanged(value: String) {
        val item = viewState.value.item
        item?.let {
            emit(viewState.value.copy(item = it.copy(description = value)))
        }
    }

    private fun obtainStartEdit() {
        emit(viewState.value.copy(isEditing = true))
    }

    private fun obtainEndEdit() {
        emit(viewState.value.copy(isEditing = false))

        checkNotNull(viewState.value.item) {
            return
        }

        launchJob {
            val area = viewState.value.item!!
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

                    onSuccess {

                    }

                    onFailure {

                    }
                }
            }
        }
    }

    private fun obtainDeleteArea() {
        checkNotNull(viewState.value.item) {
            return
        }

        launchJob {
            areasRepository.deleteArea(viewState.value.item!!.id)
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }
                        onSuccess {
                            emit(AreaDetailEvent.DeleteSuccess)
                        }
                    }
                }
        }
    }

    private fun obtainLeaveArea() {
        checkNotNull(viewState.value.item) {
            return
        }

        launchJob {
            areasRepository.leaveArea(viewState.value.item!!.id)
                .collect { result ->
                    with(result) {
                        isLoading {
                            emit(viewState.value.copy(isLoading = it))
                        }
                        onSuccess {
                            emit(AreaDetailEvent.LeaveSuccess)
                        }
                    }
                }
        }
    }

}