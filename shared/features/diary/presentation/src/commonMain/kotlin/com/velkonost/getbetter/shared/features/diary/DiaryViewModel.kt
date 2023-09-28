package com.velkonost.getbetter.shared.features.diary

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewAreaAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryAction
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryNavigation
import com.velkonost.getbetter.shared.features.diary.contracts.DiaryViewState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DiaryViewModel
internal constructor(
    private val areasRepository: AreasRepository
) : BaseViewModel<DiaryViewState, DiaryAction, DiaryNavigation, Nothing>(
    initialState = DiaryViewState()
) {

    @NativeCoroutinesState
    val createNewAreaViewModel: StateFlow<CreateNewAreaViewModel> = MutableStateFlow(
        CreateNewAreaViewModel(areasRepository)
    )

    override fun dispatch(action: DiaryAction) = when (action) {
        is CreateNewAreaAction -> dispatchCreateNewAreaAction(action)
        else -> {}
    }

    private fun dispatchCreateNewAreaAction(action: CreateNewAreaAction) {
        createNewAreaViewModel.value.dispatch(action)
        emit(
            viewState.value.copy(
                createNewAreaViewState = createNewAreaViewModel.value.viewState.value
            )
        )

    }
}