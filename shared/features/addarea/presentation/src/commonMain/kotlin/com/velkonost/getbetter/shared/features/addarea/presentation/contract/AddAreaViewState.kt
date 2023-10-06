package com.velkonost.getbetter.shared.features.addarea.presentation.contract

import com.velkonost.getbetter.shared.core.util.prefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.addarea.presentation.model.AreaUI

data class AddAreaViewState(
    val isLoading: Boolean = false,
    val items: List<AreaUI> = emptyList(),
    val loadMorePrefetch: Int = prefetchDistanceValue
) : UIContract.State