package com.velkonost.getbetter.shared.features.notedetail.presentation

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.extension.decodeFromString
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.navigation.ARG_NOTE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val SavedStateHandle.note: Flow<Note?>
    get() = getStateFlow<String?>(ARG_NOTE, null)
        .map { it?.decodeFromString() }