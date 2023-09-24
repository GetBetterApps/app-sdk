package com.velkonost.getbetter.shared.features.social.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class SocialViewState(
    val isLoading: Boolean = false
): UIContract.State