package com.velkonost.getbetter.shared.features.abilitydetails.presentation

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_ABILITY
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_IS_FAVORITE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.ability: Flow<Ability?>
    get() = getStateFlow<String?>(ARG_ABILITY, null)
        .map { it?.decodeFromString() }

internal val SavedStateHandle.isFavorite: Flow<Boolean>
    get() = getStateFlow<String?>(ARG_IS_FAVORITE, null)
        .map { it?.decodeFromString() ?: false }