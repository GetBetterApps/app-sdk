package com.velkonost.getbetter.shared.features.createnote.presentation

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.model.ui.asExternalModels
import com.velkonost.getbetter.shared.core.util.isLoading
import com.velkonost.getbetter.shared.core.util.onSuccess
import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteAction
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteEvent
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteViewState
import com.velkonost.getbetter.shared.features.diary.api.DiaryRepository
import com.velkonost.getbetter.shared.features.notes.api.NotesRepository
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

class CreateNewNoteViewModel(
    private val notesRepository: NotesRepository,
    private val diaryRepository: DiaryRepository?,
) : BaseViewModel<CreateNewNoteViewState, CreateNewNoteAction, Nothing, CreateNewNoteEvent>(
    initialState = CreateNewNoteViewState()
) {

    private var _tasksList: List<TaskUI> = emptyList()

    override fun dispatch(action: CreateNewNoteAction) = when (action) {
        is CreateNewNoteAction.InitAvailableAreas -> initAvailableAreas(action.value)
        is CreateNewNoteAction.InitTasksList -> initTasksList(action.value)
        is CreateNewNoteAction.OpenDefault -> obtainOpenDefault()
        is CreateNewNoteAction.OpenGoal -> obtainOpenGoal()
        is CreateNewNoteAction.OpenDefaultWithTask -> obtainOpenDefaultWithTask(action.value)
        is CreateNewNoteAction.OpenGoalWithTask -> obtainOpenGoalWithTask(action.value)
        is CreateNewNoteAction.AreaSelect -> obtainAreaSelect(action.value)
        is CreateNewNoteAction.TaskSelect -> obtainTaskSelect(action.value)
        is CreateNewNoteAction.TextChanged -> obtainTextChanged(action.value)
        is CreateNewNoteAction.PrivateChanged -> obtainPrivateChanged()
        is CreateNewNoteAction.NewTagTextChanged -> obtainNewTagTextChanged(action.value)
        is CreateNewNoteAction.AddNewTag -> addNewTag()
        is CreateNewNoteAction.RemoveTag -> removeTag(action.value)
        is CreateNewNoteAction.NewSubNoteTextChanged -> obtainNewSubNoteTextChanged(action.value)
        is CreateNewNoteAction.AddSubNote -> addSubNote()
        is CreateNewNoteAction.RemoveSubNote -> removeSubNote(action.value)
        is CreateNewNoteAction.AddImageUrl -> TODO()
        is CreateNewNoteAction.RemoveImageUrl -> TODO()
        is CreateNewNoteAction.SetCompletionDate -> obtainSetCompletionDate(action.value)
        is CreateNewNoteAction.CloseBecauseZeroAreas -> obtainZeroAreasError()
        is CreateNewNoteAction.CreateClick -> obtainCreateClick()
    }

    private fun initAvailableAreas(value: List<Area>) {
        emit(viewState.value.copy(availableAreas = value))
    }

    private fun initTasksList(value: List<TaskUI>) {
        _tasksList = value
    }

    private fun obtainOpenDefault() {
        val selectedArea = viewState.value.availableAreas.firstOrNull()
        val availableTasks =
            if (selectedArea != null) {
                val items: MutableList<TaskUI?> = _tasksList.asSequence().filter {
                    it.area.id == selectedArea.id
                }.toMutableList()
                items.add(0, null)

                items
            } else emptyList()

        emit(
            viewState.value.copy(
                type = NoteType.Default,
                selectedArea = selectedArea,
                availableTasks = availableTasks,
                selectedTask = null
            )
        )

        val message = Message.Builder()
            .id("hint_didid")
            .messageType(
                MessageType.Sheet.Builder()
                    .title(StringDesc.Resource(SharedR.strings.hint_diary_create_note_title))
                    .text(StringDesc.Resource(SharedR.strings.hint_diary_create_note_text))
                    .build()
            )
            .build()
        emit(message)
    }

    private fun obtainOpenDefaultWithTask(value: TaskUI) {
        val selectedArea = viewState.value.availableAreas.firstOrNull {
            it.id == value.area.id
        }
        val availableTasks =
            if (selectedArea != null) {
                val items: MutableList<TaskUI?> = _tasksList.asSequence().filter {
                    it.area.id == selectedArea.id
                }.toMutableList()
                items.add(0, null)

                items
            } else emptyList()

        emit(
            viewState.value.copy(
                type = NoteType.Default,
                selectedArea = selectedArea,
                forceSelectedArea = selectedArea,
                availableTasks = availableTasks,
                selectedTask = value,
                forceSelectedTask = value
            )
        )
    }

    private fun obtainOpenGoal() {
        val selectedArea = viewState.value.availableAreas.firstOrNull()
        val availableTasks =
            if (selectedArea != null) {
                val items: MutableList<TaskUI?> = _tasksList.asSequence().filter {
                    it.area.id == selectedArea.id
                }.toMutableList()
                items.add(0, null)

                items
            } else emptyList()

        emit(
            viewState.value.copy(
                type = NoteType.Goal,
                selectedArea = selectedArea,
                availableTasks = availableTasks,
                selectedTask = null
            )
        )
    }

    private fun obtainOpenGoalWithTask(value: TaskUI) {
        val selectedArea = viewState.value.availableAreas.firstOrNull {
            it.id == value.area.id
        }
        val availableTasks =
            if (selectedArea != null) {
                val items: MutableList<TaskUI?> = _tasksList.asSequence().filter {
                    it.area.id == selectedArea.id
                }.toMutableList()
                items.add(0, null)

                items
            } else emptyList()

        emit(
            viewState.value.copy(
                type = NoteType.Goal,
                selectedArea = selectedArea,
                forceSelectedArea = selectedArea,
                availableTasks = availableTasks,
                selectedTask = value,
                forceSelectedTask = value
            )
        )
    }


    private fun obtainAreaSelect(value: Area) {
        val availableTasks: MutableList<TaskUI?> = _tasksList.asSequence().filter {
            it.area.id == value.id
        }.toMutableList()
        availableTasks.add(0, null)

        val currentSelectedTask = viewState.value.selectedTask
        val newSelectedTask =
            if (currentSelectedTask?.area?.id == value.id) currentSelectedTask
            else null

        emit(
            viewState.value.copy(
                selectedArea = value,
                selectedTask = newSelectedTask,
                availableTasks = availableTasks,
            )
        )

        val forceSetPrivate = value.isPrivate
        if (forceSetPrivate) {
            emit(viewState.value.copy(isPrivate = true))
        }
    }

    private fun obtainTaskSelect(value: TaskUI?) {
        emit(viewState.value.copy(selectedTask = value))
    }

    private fun obtainTextChanged(value: String) {
        emit(viewState.value.copy(text = value))
    }

    private fun obtainPrivateChanged() {
        val prevValue = viewState.value.isPrivate
        emit(viewState.value.copy(isPrivate = !prevValue))
    }

    private fun obtainSetCompletionDate(value: Long?) {
        emit(viewState.value.copy(completionDate = value))
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

    private fun obtainZeroAreasError() {
        val message = Message.Builder()
            .id("error_code_message")
            .text(StringDesc.Resource(SharedR.strings.create_note_error_no_areas))
            .messageType(MessageType.SnackBar.Builder().build())
            .build()
        emit(message)
    }

    private fun obtainCreateClick() {
        val data = viewState.value

        launchJob {
            notesRepository.createNewNote(
                noteType = data.type,
                text = data.text,
                tags = data.tags.map { it.text },
                isPrivate = data.isPrivate,
                areaId = data.selectedArea!!.id,
                taskId = data.selectedTask?.id,
                subNotes = data.subNotes.asExternalModels,
                expectedCompletionDate = data.completionDate
            ) collectAndProcess {
                isLoading {
                    emit(viewState.value.copy(isLoading = it))
                }
                onSuccess {
                    saveCreatedNoteId(it?.id)
                    emit(CreateNewNoteEvent.CreatedSuccess)

                    emit(
                        viewState.value.copy(
                            type = NoteType.Goal,
                            selectedArea = viewState.value.availableAreas.firstOrNull(),
                            text = "",
                            mediaUrls = emptyList(),
                            tags = emptyList(),
                            newTag = TagUI(),
                            subNotes = emptyList(),
                            newSubNote = SubNoteUI(),
                            isPrivate = true,
                            completionDate = null
                        )
                    )
                }
            }
        }
    }

    private fun saveCreatedNoteId(noteId: Int?) {
        noteId?.let {
            launchJob {
                diaryRepository?.saveUpdatedNoteId(noteId)
            }
        }
    }
}