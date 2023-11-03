package com.velkonost.getbetter.shared.features.notedetail.presentation

import AreasRepository
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.model.ui.asTags
import com.velkonost.getbetter.shared.core.model.ui.asUI
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailNavigation
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailViewState
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import kotlinx.coroutines.flow.collectLatest

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
            note.collectLatest { note ->
                note?.let { updateData(it) }
            }
        }

    }

    override fun dispatch(action: NoteDetailAction) = when (action) {
        is NavigateBack -> emit(action)
        is NoteDetailAction.AreaChanged -> obtainAreaChanged()
        is NoteDetailAction.TextChanged -> obtainTextChanged(action.value)
        is NoteDetailAction.NewTagTextChanged -> obtainNewTagTextChanged(action.value)
        is NoteDetailAction.AddNewTag -> addNewTag()
        is NoteDetailAction.RemoveTag -> removeTag(action.value)
        is NoteDetailAction.NewSubNoteTextChanged -> obtainNewSubNoteTextChanged(action.value)
        is NoteDetailAction.AddSubNote -> addSubNote()
        is NoteDetailAction.RemoveSubNote -> removeSubNote(action.value)
        else -> {}
    }

    private fun obtainTextChanged(value: String) {
        emit(viewState.value.copy(noteText = value))
    }

    private fun obtainNewTagTextChanged(value: String) {
        if (value.isNotEmpty() && value.last() == ' ') {
            addNewTag()
        } else {
            emit(
                viewState.value.copy(
                    newTag = TagUI(text = value.replace(" ", "").take(15))
                )
            )
        }
    }

    private fun addNewTag() {
        val tagText = viewState.value.newTag.text.replace(" ", "")
        val tagsList = viewState.value.tags

        if (tagsList.none { it.text == tagText } && tagText.isNotEmpty()) {
            emit(
                viewState.value.copy(
                    newTag = TagUI(),
                    tags = tagsList.plus(TagUI(text = tagText))
                )
            )
        } else {
            obtainNewTagTextChanged(tagText)
        }
    }

    private fun removeTag(value: String) {
        val tagsList = viewState.value.tags

        tagsList.firstOrNull { it.text == value }?.let {
            emit(viewState.value.copy(tags = tagsList.minus(it)))
        }
    }

    private fun obtainNewSubNoteTextChanged(value: String) {
        emit(viewState.value.copy(newSubNote = SubNoteUI(text = value)))
    }

    private fun addSubNote() {
        val subNote = viewState.value.newSubNote
        val subNotesList = viewState.value.subNotes

        if (!subNotesList.contains(subNote) && subNote.text.isNotEmpty()) {
            emit(
                viewState.value.copy(
                    newSubNote = SubNoteUI(),
                    subNotes = subNotesList.plus(subNote)
                )
            )
        }
    }

    private fun removeSubNote(value: SubNoteUI) {
        val subNotesList = viewState.value.subNotes

        emit(
            viewState.value.copy(
                subNotes = subNotesList.filter { it.id != value.id }
            )
        )
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

    private fun updateData(value: Note) {
        emit(
            viewState.value.copy(
                isNotePrivate = value.isPrivate,
                noteType = value.noteType,
                area = value.area,
                noteText = value.text,
                tags = value.tags.asTags,
                newTag = TagUI(),
                subNotes = value.subNotes.asUI,
                expectedCompletionDate = value.expectedCompletionDate,
                expectedCompletionDateStr = value.expectedCompletionDateStr,
                completionDate = value.completionDate,
                completionDateStr = value.completionDateStr,
                allowEdit = value.allowEdit
            )
        )
    }

}