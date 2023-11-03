package com.velkonost.getbetter.shared.features.notedetail.presentation.contract

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.core.model.ui.SubNoteUI
import com.velkonost.getbetter.shared.core.model.ui.TagUI
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class NoteDetailViewState(
    val isLoading: Boolean = false,
    val isCompleteGoalLoading: Boolean = false,

    val initialItem: Note? = null,

    val noteState: State = State.View,

    val noteType: NoteType? = null,
    val isNotePrivate: Boolean = true,
    val noteText: String = "",

    val tags: List<TagUI> = emptyList(),
    val newTag: TagUI = TagUI(),

    val subNotes: List<SubNoteUI> = emptyList(),
    val newSubNote: SubNoteUI = SubNoteUI(),

    val expectedCompletionDate: Long? = null,
    val completionDate: Long? = null,

    val expectedCompletionDateStr: String? = expectedCompletionDate?.convertToLocalDatetime(),
    val completionDateStr: String? = completionDate?.convertToLocalDatetime(),

    val area: Area? = null,

    val allowEdit: Boolean = false
) : UIContract.State

enum class State {
    View, Editing
}