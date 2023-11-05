package com.velkonost.getbetter.shared.features.social

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.social.api.SocialRepository
import com.velkonost.getbetter.shared.features.social.contracts.SocialAction
import com.velkonost.getbetter.shared.features.social.contracts.SocialNavigation
import com.velkonost.getbetter.shared.features.social.contracts.SocialViewState

class SocialViewModel
internal constructor(
    private val socialRepository: SocialRepository
) : BaseViewModel<SocialViewState, SocialAction, SocialNavigation, Nothing>(
    initialState = SocialViewState()
) {

    private val _generalFeedPagingConfig = PagingConfig()
    private val _areasFeedPagingConfig = PagingConfig()

    override fun dispatch(action: SocialAction) = when (action) {

        else -> {}
    }

}