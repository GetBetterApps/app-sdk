package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class DiaryViewState(
    val isLoading: Boolean = false
): UIContract.State