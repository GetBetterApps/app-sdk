package com.velkonost.getbetter.shared.features.social

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.social.models.SocialAction
import com.velkonost.getbetter.shared.features.social.models.SocialNavigation
import com.velkonost.getbetter.shared.features.social.models.SocialViewState

class SocialViewModel
internal constructor(

) : BaseViewModel<SocialViewState, SocialAction, SocialNavigation, Nothing>(
    initialState = SocialViewState()
) {
    override fun dispatch(action: SocialAction) = when(action) {

        else -> {}
    }

}