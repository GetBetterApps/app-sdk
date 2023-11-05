package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.social.model.SocialTab

data class SocialViewState(
    val isLoading: Boolean = false,
    val tabs: List<SocialTab> = SocialTab.values().toList(),
) : UIContract.State

data class GeneralFeedViewState(
    val isLoading: Boolean = true,
    val items: List<Note>
) : UIContract.State