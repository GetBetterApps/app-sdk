package com.velkonost.getbetter.shared.features.diary.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.hint.UIHint
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.api.DiaryRepository
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.CreateNewAreaEvent
import com.velkonost.getbetter.shared.features.diary.presentation.contracts.CreateNewAreaViewState

class CreateNewAreaViewModel
internal constructor(
    private val areasRepository: AreasRepository,
    private val diaryRepository: DiaryRepository
) : BaseViewModel<CreateNewAreaViewState, CreateNewAreaAction, Nothing, CreateNewAreaEvent>(
    initialState = CreateNewAreaViewState(selectedEmoji = Emoji.entries.first())
) {
    override fun dispatch(action: CreateNewAreaAction) = when (action) {
        is CreateNewAreaAction.Open -> obtainOpen()
        is CreateNewAreaAction.EmojiSelect -> obtainEmojiSelect(action.value)
        is CreateNewAreaAction.NameChanged -> obtainNameChanged(action.value)
        is CreateNewAreaAction.DescriptionChanged -> obtainDescriptionChanged(action.value)
        is CreateNewAreaAction.RequiredLevelChanged -> obtainRequiredLevelChanged(action.value)
        is CreateNewAreaAction.PrivateChanged -> obtainPrivateChanged()
        is CreateNewAreaAction.CreateClick -> obtainCreateClick()
        is CreateNewAreaAction.HintClick -> showHint()
    }

    private fun showHint(firstTime: Boolean = false) {
        if (firstTime) {
            launchJob {
                if (diaryRepository.shouldShowCreateAreaHint()) {
                    UIHint.DiaryCreateArea.send(isMain = false)
                }
            }
        } else UIHint.DiaryCreateArea.send(isMain = false)
    }

    private fun obtainOpen() {
        emit(initialState)
        showHint(firstTime = true)
    }

    private fun obtainPrivateChanged() {
        val prevValue = viewState.value.isPrivate
        emit(viewState.value.copy(isPrivate = !prevValue))
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
        if (validateAreaName() && validateAreaDescription()) {
            launchJob {
                val areaData = viewState.value
                areasRepository.createNewArea(
                    name = areaData.name,
                    description = areaData.description,
                    requiredLevel = areaData.requiredLevel,
                    emojiId = areaData.selectedEmoji.id,
                    isPrivate = areaData.isPrivate,
                    imageUrl = null
                ) collectAndProcess {
                    isLoading {
                        emit(viewState.value.copy(isLoading = it))
                    }
                    onSuccess {
                        emit(CreateNewAreaEvent.CreatedSuccess)
                    }
                }
            }
        }
    }

    private fun validateAreaName(): Boolean {
        val nameValue = viewState.value.name
        return nameValue.trim().isNotBlank()
    }

    private fun validateAreaDescription(): Boolean {
        val descriptionValue = viewState.value.description
        return descriptionValue.trim().isNotBlank()
    }
}