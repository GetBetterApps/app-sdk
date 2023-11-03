package com.velkonost.getbetter.shared.features.notedetail.presentation

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.model.ui.asTags
import com.velkonost.getbetter.shared.core.model.ui.asUI
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.AreaChanged
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailNavigation
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailViewState
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository

class NoteDetailViewModel
internal constructor(
    savedStateHandle: SavedStateHandle,
    private val areasRepository: AreasRepository,
    private val notesRepository: NotesRepository
) : BaseViewModel<NoteDetailViewState, NoteDetailAction, NoteDetailNavigation, NoteDetailEvent>(
    initialState = NoteDetailViewState(),
    savedStateHandle = savedStateHandle
) {

    @NativeCoroutinesState
    private val note = savedStateHandle.note.stateInWhileSubscribed(initialValue = null)

    init {
        launchJob {
            note.collect { note ->
                note?.let {
                    emit(
                        viewState.value.copy(
                            isNotePrivate = it.isPrivate,
                            noteType = it.noteType,
                            area = it.area,
                            noteText = it.text,
                            tags = it.tags.asTags,
                            newTag = TagUI(),
                            subNotes = it.subNotes.asUI,
                            expectedCompletionDate = it.expectedCompletionDate,
                            expectedCompletionDateStr = it.expectedCompletionDateStr,
                            completionDate = it.completionDate,
                            completionDateStr = it.completionDateStr,
                            allowEdit = it.allowEdit
                        )
                    )
                }

            }
        }

    }

    override fun dispatch(action: NoteDetailAction) = when (action) {
        is NavigateBack -> emit(action)
        is AreaChanged -> obtainAreaChanged()
        else -> {}
    }

    private fun obtainAreaChanged() {
        launchJob {
            viewState.value.area?.let { area ->
                areasRepository.fetchAreaDetails(area.id)
                    .collect { result ->
                        with(result) {
                            onSuccess {
                                emit(viewState.value.copy(area = it))
                            }
                            onFailureWithMsg { _, message ->
                                message?.let { emit(it) }
                            }
                        }
                    }
            }

        }
    }

}