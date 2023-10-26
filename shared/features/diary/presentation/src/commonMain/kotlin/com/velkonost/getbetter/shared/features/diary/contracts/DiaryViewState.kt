package com.velkonost.getbetter.shared.features.diary.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.NoteType
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.diary.model.DiaryTab
import com.velkonost.getbetter.shared.features.diary.model.SubNoteUI
import com.velkonost.getbetter.shared.features.diary.model.TagUI
import model.Area

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
    val isLoading: Boolean = false,
) : UIContract.State

data class AreasViewState(
    val isLoading: Boolean = false,
    val items: List<Area> = emptyList()
) : UIContract.State

data class TasksViewState(
    val isLoading: Boolean = false,
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
    val isLoading: Boolean = false,
    val type: NoteType = NoteType.Default,
    val selectedArea: Area? = availableAreas.firstOrNull(),
    val text: String = "",
    val mediaUrls: List<String> = emptyList(),

    val tags: List<TagUI> = emptyList(),
    val newTag: TagUI = TagUI(),

    val subNotes: List<SubNoteUI> = emptyList(),
    val newSubNoteText: SubNoteUI = SubNoteUI(),

    val isPrivate: Boolean = true
) : UIContract.State