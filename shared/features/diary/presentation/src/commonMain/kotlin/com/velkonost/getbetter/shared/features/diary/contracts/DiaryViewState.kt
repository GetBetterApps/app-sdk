package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.diary.model.DiaryTab

data class DiaryViewState(
    val isLoading: Boolean = false,
    val tabs: List<DiaryTab> = DiaryTab.values().toList(),
    val emojiList: List<Emoji> = Emoji.values().toList(),
    val createNewAreaViewState: CreateNewAreaViewState = CreateNewAreaViewState(
        selectedEmoji = emojiList.first()
    )
) : UIContract.State

data class CreateNewAreaViewState(
    val selectedEmoji: Emoji,
    val name: String = "",
    val description: String = "",
    val requiredLevel: Int = 1
) : UIContract.State