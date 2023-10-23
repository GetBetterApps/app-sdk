package com.velkonost.getbetter.shared.features.areadetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface AreaDetailEvent : UIContract.Event {
    data class DeleteSuccess(val areaId: Int) : AreaDetailEvent

    data class LeaveSuccess(val areaId: Int) : AreaDetailEvent

    data class EditSuccess(val areaId: Int) : AreaDetailEvent

    data class JoinSuccess(val areaId: Int) : AreaDetailEvent
}