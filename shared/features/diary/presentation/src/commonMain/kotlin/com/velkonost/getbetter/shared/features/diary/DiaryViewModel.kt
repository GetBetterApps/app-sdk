package com.velkonost.getbetter.shared.features.diary

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.models.DiaryAction
import com.velkonost.getbetter.shared.features.diary.models.DiaryNavigation
import com.velkonost.getbetter.shared.features.diary.models.DiaryViewState

class DiaryViewModel
internal constructor(

) : BaseViewModel<DiaryViewState, DiaryAction, DiaryNavigation, Nothing>(
    initialState = DiaryViewState()
) {

    override fun dispatch(action: DiaryAction) = when(action) {

        else -> {}
    }

}