package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface CalendarsAction : UIContract.Action {

    data object LoadMoreNextDates : CalendarsAction

    data class DateClick(val id: Long) : CalendarsAction

    data class NoteClick(val value: Note) : CalendarsAction

    data class TaskClick(val value: TaskUI) : CalendarsAction

    data object HintClick : CalendarsAction
}