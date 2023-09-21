package com.velkonost.getbetter.shared.features.diary.models

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class DiaryViewState(
    val isLoading: Boolean = false
): UIContract.State