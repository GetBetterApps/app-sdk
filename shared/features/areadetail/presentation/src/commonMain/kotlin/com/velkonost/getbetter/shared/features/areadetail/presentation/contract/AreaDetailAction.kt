package com.velkonost.getbetter.shared.features.areadetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface AreaDetailAction : UIContract.Action {
    data class Load(val areaId: Int) : AreaDetailAction

    data class EmojiChanged(val value: Emoji) : AreaDetailAction

    data class NameChanged(val value: String) : AreaDetailAction

    data class DescriptionChanged(val value: String) : AreaDetailAction

    data object LikeClick : AreaDetailAction

    data object StartEdit : AreaDetailAction

    data object EndEdit : AreaDetailAction

    data object DeleteClick : AreaDetailAction

    data object LeaveClick : AreaDetailAction

    data object JoinClick : AreaDetailAction

    data object CancelEdit : AreaDetailAction

}