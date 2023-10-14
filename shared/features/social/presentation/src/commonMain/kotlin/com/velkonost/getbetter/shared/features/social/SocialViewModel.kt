package com.velkonost.getbetter.shared.features.social

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.social.contracts.SocialAction
import com.velkonost.getbetter.shared.features.social.contracts.SocialNavigation
import com.velkonost.getbetter.shared.features.social.contracts.SocialViewState

class SocialViewModel
internal constructor(

) : BaseViewModel<SocialViewState, SocialAction, SocialNavigation, Nothing>(
    initialState = SocialViewState()
) {
    override fun dispatch(action: SocialAction) = when (action) {

        else -> {}
    }

}