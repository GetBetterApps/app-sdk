package com.velkonost.getbetter.shared.features.diary

import AreasRepository
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaViewState

class CreateNewAreaViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<CreateNewAreaViewState, CreateNewAreaAction, Nothing, Nothing>(
    initialState = CreateNewAreaViewState(selectedEmoji = Emoji.values().first())
) {
    override fun dispatch(action: CreateNewAreaAction) = when (action) {
        is CreateNewAreaAction.Open -> obtainOpen()
        is CreateNewAreaAction.EmojiSelect -> obtainEmojiSelect(action.value)
        is CreateNewAreaAction.NameChanged -> obtainNameChanged(action.value)
        is CreateNewAreaAction.DescriptionChanged -> obtainDescriptionChanged(action.value)
        is CreateNewAreaAction.RequiredLevelChanged -> obtainRequiredLevelChanged(action.value)
        is CreateNewAreaAction.CreateClick -> obtainCreateClick()
    }

    private fun obtainOpen() {
        emit(initialState)
    }

    private fun obtainEmojiSelect(value: Emoji) {
        emit(viewState.value.copy(selectedEmoji = value))
    }

    private fun obtainNameChanged(value: String) {
        emit(viewState.value.copy(name = value))
    }

    private fun obtainDescriptionChanged(value: String) {
        emit(viewState.value.copy(description = value))
    }

    private fun obtainRequiredLevelChanged(value: Int) {
        if (value in 1..10) {
            emit(viewState.value.copy(requiredLevel = value))
        }
    }

    private fun obtainCreateClick() {

    }
}