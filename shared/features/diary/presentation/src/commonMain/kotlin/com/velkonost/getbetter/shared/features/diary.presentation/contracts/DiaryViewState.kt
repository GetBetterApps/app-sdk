package com.velkonost.getbetter.shared.features.diary.presentation.contracts

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.PrefetchDistanceValue
import com.velkonost.getbetter.shared.core.util.advertise.ACTUAL_AD_ID
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.features.createnote.presentation.contract.CreateNewNoteViewState
import com.velkonost.getbetter.shared.features.diary.presentation.model.DiaryTab

data class DiaryViewState(
    val tabs: List<DiaryTab> = DiaryTab.entries,
    val emojiList: List<Emoji> = Emoji.entries,
    val notesViewState: NotesViewState = NotesViewState(),
    val areasViewState: AreasViewState = AreasViewState(),
    val tasksViewState: TasksViewState = TasksViewState(),
    val createNewAreaViewState: CreateNewAreaViewState = CreateNewAreaViewState(
        selectedEmoji = emojiList.first()
    ),
    val createNewNoteViewState: CreateNewNoteViewState = CreateNewNoteViewState(),

    val adId: String = ACTUAL_AD_ID,
    val adPosition: Int = (6..10).random(),
    val showAds: Boolean = false,

    val isTrialLoading: Boolean = false
) : UIContract.State

data class NotesViewState(
    val isLoading: Boolean = true,
    val items: List<Note> = emptyList(),
    val loadMorePrefetch: Int = PrefetchDistanceValue
) : UIContract.State

data class AreasViewState(
    val isLoading: Boolean = true,
    val items: List<Area> = emptyList(),
    val canCreateNewArea: Boolean = false
) : UIContract.State

data class TasksViewState(
    val isLoading: Boolean = true,
    val canUpdateList: Boolean = false,
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
