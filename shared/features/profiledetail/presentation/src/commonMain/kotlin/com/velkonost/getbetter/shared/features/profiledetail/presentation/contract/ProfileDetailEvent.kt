package com.velkonost.getbetter.shared.features.profiledetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface ProfileDetailEvent : UIContract.Event {

    data object BlockSuccess : ProfileDetailEvent

}