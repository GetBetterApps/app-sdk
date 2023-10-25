package com.velkonost.getbetter.shared.features.diary

import com.velkonost.getbetter.shared.core.model.NoteType
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteEvent
import com.velkonost.getbetter.shared.features.diary.contracts.CreateNewNoteViewState
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc
import model.Area

class CreateNewNoteViewModel
internal constructor(
    private val notesRepository: NotesRepository
) : BaseViewModel<CreateNewNoteViewState, CreateNewNoteAction, Nothing, CreateNewNoteEvent>(
    initialState = CreateNewNoteViewState()
) {
    override fun dispatch(action: CreateNewNoteAction) = when (action) {
        is CreateNewNoteAction.InitAvailableAreas -> initAvailableAreas(action.value)
        is CreateNewNoteAction.OpenDefault -> obtainOpenDefault()
        is CreateNewNoteAction.OpenGoal -> obtainOpenGoal()
        is CreateNewNoteAction.AreaSelect -> obtainAreaSelect(action.value)
        is CreateNewNoteAction.TextChanged -> obtainTextChanged(action.value)
        is CreateNewNoteAction.NewTagTextChanged -> obtainNewTagTextChanged(action.value)
        is CreateNewNoteAction.AddNewTag -> addNewTag()
        is CreateNewNoteAction.RemoveTag -> removeTag(action.value)
        is CreateNewNoteAction.NewSubNoteTextChanged -> obtainNewSubNoteTextChanged(action.value)
        is CreateNewNoteAction.AddSubNote -> addSubNote()
        is CreateNewNoteAction.RemoveSubNote -> removeSubNote(action.value)
        is CreateNewNoteAction.AddImageUrl -> TODO()
        is CreateNewNoteAction.RemoveImageUrl -> TODO()
        is CreateNewNoteAction.CloseBecauseZeroAreas -> obtainZeroAreasError()
    }

    private fun initAvailableAreas(value: List<Area>) {
        emit(viewState.value.copy(availableAreas = value))
    }

    private fun obtainOpenDefault() {
        emit(
            viewState.value.copy(
                type = NoteType.Default,
                selectedArea = viewState.value.availableAreas.firstOrNull(),
                text = "",
                mediaUrls = emptyList(),
                tags = emptyList(),
                newTagText = "",
                subNotes = emptyList(),
                newSubNoteText = ""
            )
        )

    }

    private fun obtainOpenGoal() {
        emit(
            viewState.value.copy(
                type = NoteType.Goal,
                selectedArea = viewState.value.availableAreas.firstOrNull(),
                text = "",
                mediaUrls = emptyList(),
                tags = emptyList(),
                newTagText = "",
                subNotes = emptyList(),
                newSubNoteText = ""
            )
        )
    }

    private fun obtainAreaSelect(value: Area) {
        emit(viewState.value.copy(selectedArea = value))
    }

    private fun obtainTextChanged(value: String) {
        emit(viewState.value.copy(text = value))
    }

    private fun obtainNewTagTextChanged(value: String) {
        emit(viewState.value.copy(newTagText = value))
    }

    private fun addNewTag() {
        val tag = viewState.value.newTagText
        val tagsList = viewState.value.tags

        if (!tagsList.contains(tag)) {
            emit(
                viewState.value.copy(
                    newTagText = "",
                    tags = tagsList.plus(tag)
                )
            )
        }
    }

    private fun removeTag(value: String) {
        val tagsList = viewState.value.tags

        if (tagsList.contains(value)) {
            emit(viewState.value.copy(tags = tagsList.minus(value)))
        }
    }

    private fun obtainNewSubNoteTextChanged(value: String) {
        emit(viewState.value.copy(newSubNoteText = value))
    }

    private fun addSubNote() {
        val subNote = viewState.value.newSubNoteText
        val subNotesList = viewState.value.subNotes

        if (!subNotesList.contains(subNote)) {
            emit(
                viewState.value.copy(
                    newSubNoteText = "",
                    subNotes = subNotesList.plus(subNote)
                )
            )
        }
    }

    private fun removeSubNote(value: String) {
        val subNotesList = viewState.value.subNotes

        if (subNotesList.contains(value)) {
            emit(viewState.value.copy(subNotes = subNotesList.minus(value)))
        }
    }

    private fun obtainZeroAreasError() {
        val message = Message.Builder()
            .id("error_code_message")
            .text(StringDesc.Resource(SharedR.strings.create_note_error_no_areas))
            .messageType(MessageType.SnackBar.Builder().build())
            .build()
        emit(message)
    }

}