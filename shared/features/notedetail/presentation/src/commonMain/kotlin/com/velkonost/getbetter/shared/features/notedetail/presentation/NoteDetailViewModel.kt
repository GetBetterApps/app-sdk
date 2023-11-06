package com.velkonost.getbetter.shared.features.notedetail.presentation

import AreasRepository
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.model.ui.asExternalModels
import com.velkonost.getbetter.shared.core.model.ui.asTags
import com.velkonost.getbetter.shared.core.model.ui.asUI
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.SavedStateHandle
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NavigateBack
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailAction
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailEvent
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailNavigation
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteDetailViewState
import com.velkonost.getbetter.shared.features.notedetail.presentation.contract.NoteState
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

    private val note = savedStateHandle.note.stateInWhileSubscribed(initialValue = null)

    init {
        launchJob {
            note.collectLatest { note ->
                note?.updateUI()
            }
        }
    }

    override fun dispatch(action: NoteDetailAction) = when (action) {
        is NavigateBack -> emit(action)
        is NoteDetailAction.AreaChanged -> obtainAreaChanged()
        is NoteDetailAction.TextChanged -> obtainTextChanged(action.value)
        is NoteDetailAction.SetCompletionDate -> obtainSetCompletionDate(action.value)
        is NoteDetailAction.NewTagTextChanged -> obtainNewTagTextChanged(action.value)
        is NoteDetailAction.AddNewTag -> addNewTag()
        is NoteDetailAction.RemoveTag -> removeTag(action.value)
        is NoteDetailAction.NewSubNoteTextChanged -> obtainNewSubNoteTextChanged(action.value)
        is NoteDetailAction.AddSubNote -> addSubNote()
        is NoteDetailAction.RemoveSubNote -> removeSubNote(action.value)
        is NoteDetailAction.StartEditClick -> obtainStartEdit()
        is NoteDetailAction.CancelEditClick -> obtainCancelEdit()
        is NoteDetailAction.EndEditClick -> obtainSave()
        is NoteDetailAction.DeleteClick -> obtainDelete()
        is NoteDetailAction.CompleteClick -> completeGoal()
        is NoteDetailAction.CompleteSubNoteClick -> completeSubGoal(action.value)
        is NoteDetailAction.UnCompleteClick -> unCompleteGoal()
        is NoteDetailAction.UnCompleteSubNoteClick -> unCompleteSubGoal(action.value)
    }

    private fun obtainTextChanged(value: String) {
        emit(viewState.value.copy(noteText = value))
    }

    private fun obtainSetCompletionDate(value: Long?) {
        emit(viewState.value.copy(expectedCompletionDate = value))
    }

    private fun obtainStartEdit() {
        emit(viewState.value.copy(noteState = NoteState.Editing))
    }

    private fun obtainCancelEdit() {
        val initialItem = viewState.value.initialItem

        checkNotNull(initialItem) { return }

        initialItem.updateUI()
        emit(viewState.value.copy(noteState = NoteState.View))
    }

    private fun obtainSave() {
        emit(viewState.value.copy(noteState = NoteState.View))

        launchJob {
            notesRepository.editNote(
                noteId = viewState.value.initialItem!!.id,
                text = viewState.value.noteText,
                tags = viewState.value.tags.map { it.text },
                subNotes = viewState.value.subNotes.asExternalModels,
                completionDate = null,
                expectedCompletionDate = viewState.value.expectedCompletionDate
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess { note ->
                    note?.updateUI()
                    emit(NoteDetailEvent.EditSuccess)
                }
            }
        }
    }

    private fun obtainDelete() {
        launchJob {
            notesRepository.deleteNote(
                noteId = viewState.value.initialItem!!.id
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    emit(NoteDetailEvent.DeleteSuccess)
                }
            }
        }
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

        emit(viewState.value.copy(subNotes = subNotesList.filter { it.id != value.id }))
    }


    private fun obtainAreaChanged() {
        launchJob {
            viewState.value.area?.let { area ->
                areasRepository.fetchAreaDetails(area.id) collectAndProcess {
                    onSuccess {
                        emit(viewState.value.copy(area = it))
                    }
                }
            }
        }
    }

    private fun completeGoal() {
        launchJob {
            notesRepository.completeGoal(
                noteId = viewState.value.initialItem!!.id
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isCompleteGoalLoading = it))
                }
                onSuccess {
                    it?.updateUI()
                }
            }
        }
    }

    private fun completeSubGoal(value: SubNoteUI) {
        launchJob {
            notesRepository.completeSubGoal(
                noteId = viewState.value.initialItem!!.id,
                subNoteId = value.id.toInt()
            ) collectAndProcess {
                onSuccess {
                    it?.updateUI()
                }
            }
        }
    }

    private fun unCompleteGoal() {
        launchJob {
            notesRepository.unCompleteGoal(
                noteId = viewState.value.initialItem!!.id
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isCompleteGoalLoading = it))
                }
                onSuccess {
                    it?.updateUI()
                }
            }
        }
    }

    private fun unCompleteSubGoal(value: SubNoteUI) {
        launchJob {
            notesRepository.unCompleteSubGoal(
                noteId = viewState.value.initialItem!!.id,
                subNoteId = value.id.toInt()
            ) collectAndProcess {
                onSuccess {
                    it?.updateUI()
                }
            }
        }
    }

    private fun Note.updateUI() {
        emit(
            viewState.value.copy(
                initialItem = this,
                isNotePrivate = isPrivate,
                noteType = noteType,
                area = area,
                noteText = text,
                tags = tags.asTags,
                newTag = TagUI(),
                subNotes = subNotes.asUI,
                expectedCompletionDate = expectedCompletionDate,
                expectedCompletionDateStr = expectedCompletionDateStr,
                completionDate = completionDate,
                completionDateStr = completionDateStr,
                allowEdit = allowEdit
            )
        )
    }

}