package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface DiaryAction : UIContract.Action

data object AddAreaClick : DiaryAction

sealed interface CreateNewAreaAction : DiaryAction {
    data object Open : CreateNewAreaAction
    data class EmojiSelect(val value: Emoji) : CreateNewAreaAction
    data class NameChanged(val value: String) : CreateNewAreaAction
    data class DescriptionChanged(val value: String) : CreateNewAreaAction
    data class RequiredLevelChanged(val value: Int) : CreateNewAreaAction
    data object PrivateChanged : CreateNewAreaAction
    data object CreateClick : CreateNewAreaAction
}


