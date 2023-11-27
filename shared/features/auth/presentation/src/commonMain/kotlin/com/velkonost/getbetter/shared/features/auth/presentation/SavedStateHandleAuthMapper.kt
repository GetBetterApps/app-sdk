package com.velkonost.getbetter.shared.features.auth.presentation

import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IDENTIFY_ANONYMOUS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.identifyAnonymous: Flow<Boolean>
    get() = getStateFlow<String?>(ARG_IDENTIFY_ANONYMOUS, null)
        .map { it?.decodeFromString() ?: false }