package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class NoteDetailViewState(
    val isLoading: Boolean = false
) : UIContract.State