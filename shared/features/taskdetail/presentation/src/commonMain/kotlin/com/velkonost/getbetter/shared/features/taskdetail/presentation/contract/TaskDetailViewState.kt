package com.velkonost.getbetter.shared.features.taskdetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class TaskDetailViewState(
    val isLoading: Boolean = false
) : UIContract.State