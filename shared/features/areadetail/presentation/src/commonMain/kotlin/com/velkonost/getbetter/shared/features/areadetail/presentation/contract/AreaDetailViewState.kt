package com.velkonost.getbetter.shared.features.areadetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.areadetail.presentation.model.AreaDetailUI

data class AreaDetailViewState(
    val isLoading: Boolean = false,
    val isEditing: Boolean = false,
    val isAllowJoin: Boolean = false,
    val isAllowDelete: Boolean = false,
    val isAllowLeave: Boolean = false,
    val isAllowEdit: Boolean = false,
    val initialItem: AreaDetailUI? = null,
    val modifiedItem: AreaDetailUI? = null
) : UIContract.State