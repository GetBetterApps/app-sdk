package com.velkonost.getbetter.shared.features.taskdetail.presentation

import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_TASK
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.task: Flow<TaskUI?>
    get() = getStateFlow<String?>(ARG_TASK, null)
        .map { it?.decodeFromString() }