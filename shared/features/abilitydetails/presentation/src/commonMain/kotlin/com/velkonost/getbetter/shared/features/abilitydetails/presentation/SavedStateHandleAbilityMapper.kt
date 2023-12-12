package com.velkonost.getbetter.shared.features.abilitydetails.presentation

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_ABILITY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.ability: Flow<Ability?>
    get() = getStateFlow<String?>(ARG_ABILITY, null)
        .map { it?.decodeFromString() }