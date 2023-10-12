package com.velkonost.getbetter.shared.features.diary

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteEvent
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteViewState
import com.velkonost.getbetter.shared.features.diary.model.NoteType

class CreateNewNoteViewModel
internal constructor(

) : BaseViewModel<CreateNewNoteViewState, CreateNewNoteAction, Nothing, CreateNewNoteEvent>(
    initialState = CreateNewNoteViewState()
) {
    override fun dispatch(action: CreateNewNoteAction) = when (action) {
        is CreateNewNoteAction.OpenDefault -> obtainOpenDefault()
        is CreateNewNoteAction.OpenGoal -> obtainOpenGoal()
    }

    private fun obtainOpenDefault() {
        emit(initialState)
    }

    private fun obtainOpenGoal() {
        emit(initialState.copy(type = NoteType.Goal))
    }
}