package com.velkonost.getbetter.shared.features.diary

import AreasRepository
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryNavigation
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryViewState

class DiaryViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<DiaryViewState, DiaryAction, DiaryNavigation, Nothing>(
    initialState = DiaryViewState()
) {

    override fun dispatch(action: DiaryAction) = when(action) {

        else -> {}
    }

}