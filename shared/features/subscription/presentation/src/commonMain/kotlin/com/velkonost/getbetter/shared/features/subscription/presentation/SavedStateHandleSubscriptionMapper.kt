package com.velkonost.getbetter.shared.features.subscription.presentation

import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_RETURN_TO_PROFILE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.returnToProfile: Flow<Boolean>
    get() = getStateFlow<String?>(ARG_RETURN_TO_PROFILE, null)
        .map { it?.decodeFromString() ?: false }