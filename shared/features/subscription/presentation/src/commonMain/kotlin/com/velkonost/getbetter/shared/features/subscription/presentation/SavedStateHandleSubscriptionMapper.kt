package com.velkonost.getbetter.shared.features.subscription.presentation

import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_GO_PAYWALL
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IDENTIFY_ANONYMOUS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.returnToProfile: Flow<Boolean>
    get() = getStateFlow<String?>(ARG_IDENTIFY_ANONYMOUS, null)
        .map { it?.decodeFromString() ?: false }

internal val SavedStateHandle.showPaywallNext: Flow<Boolean>
    get() = getStateFlow<String?>(ARG_GO_PAYWALL, null)
        .map { it?.decodeFromString() ?: false }