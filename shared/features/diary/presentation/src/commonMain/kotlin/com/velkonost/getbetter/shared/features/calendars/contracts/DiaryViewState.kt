package com.velkonost.getbetter.shared.features.calendars.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.calendars.model.DiaryTab

data class DiaryViewState(
    val tabs: List<DiaryTab> = DiaryTab.values().toList(),
    val emojiList: List<Emoji> = Emoji.values().toList(),
    val notesViewState: NotesViewState = NotesViewState(),
    val areasViewState: AreasViewState = AreasViewState(),
    val tasksViewState: TasksViewState = TasksViewState(),
    val createNewAreaViewState: CreateNewAreaViewState = CreateNewAreaViewState(
        selectedEmoji = emojiList.first()
    ),
    val createNewNoteViewState: CreateNewNoteViewState = CreateNewNoteViewState()
) : UIContract.State

data class NotesViewState(
    val isLoading: Boolean = true,
    val items: List<Note> = emptyList(),
    val loadMorePrefetch: Int = PrefetchDistanceValue
) : UIContract.State

data class AreasViewState(
    val isLoading: Boolean = true,
    val items: List<Area> = emptyList()
) : UIContract.State

data class TasksViewState(
    val isLoading: Boolean = true,
    val favoriteItems: List<TaskUI> = emptyList(),
    val currentItems: List<TaskUI> = emptyList(),
    val completedItems: List<TaskUI> = emptyList()
) : UIContract.State

data class CreateNewAreaViewState(
    val isLoading: Boolean = false,
    val selectedEmoji: Emoji,
    val name: String = "",
    val description: String = "",
    val requiredLevel: Int = 1,
    val isPrivate: Boolean = true
) : UIContract.State

data class CreateNewNoteViewState(
    val availableAreas: List<Area> = emptyList(),
    val availableTasks: List<TaskUI> = emptyList(),

    val isLoading: Boolean = false,
    val type: NoteType = NoteType.Default,

    val selectedArea: Area? = availableAreas.firstOrNull(),
    val selectedTask: TaskUI? = null,

    val text: String = "",
    val mediaUrls: List<String> = emptyList(),

    val tags: List<TagUI> = emptyList(),
    val newTag: TagUI = TagUI(),

    val subNotes: List<SubNoteUI> = emptyList(),
    val newSubNote: SubNoteUI = SubNoteUI(),

    val isPrivate: Boolean = true,
    val completionDate: Long? = null
) : UIContract.State