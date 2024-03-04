package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface SocialEvent : UIContract.Event {

    data object SuggestResumeSubscription : SocialEvent

}