package com.velkonost.getbetter.shared.features.createnote.presentation.contract

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract


data class CreateNewNoteViewState(
    val availableAreas: List<Area> = emptyList(),
    val availableTasks: List<TaskUI?> = emptyList(),

    val isLoading: Boolean = false,
    val type: NoteType = NoteType.Default,

    val selectedArea: Area? = availableAreas.firstOrNull(),
    val selectedTask: TaskUI? = null,

    val forceSelectedArea: Area? = null,
    val forceSelectedTask: TaskUI? = null,

    val text: String = "",
    val mediaUrls: List<String> = emptyList(),

    val tags: List<TagUI> = emptyList(),
    val newTag: TagUI = TagUI(),

    val subNotes: List<SubNoteUI> = emptyList(),
    val newSubNote: SubNoteUI = SubNoteUI(),

    val isPrivate: Boolean = true,
    val completionDate: Long? = null
) : UIContract.State

