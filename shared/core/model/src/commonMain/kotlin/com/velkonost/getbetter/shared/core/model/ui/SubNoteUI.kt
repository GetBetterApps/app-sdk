package com.velkonost.getbetter.shared.core.model.ui

import com.velkonost.getbetter.shared.core.model.note.SubNote
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import com.velkonost.getbetter.shared.core.util.randomUUID
import dev.icerock.moko.resources.desc.StringDesc

data class SubNoteUI(
    val id: String = randomUUID(),
    val text: String = "",
    val completionDate: Long? = null,
    val expectedCompletionDate: Long? = null
) {
    val completionDateStr: StringDesc?
        get() = completionDate?.convertToLocalDatetime()

    val expectedCompletionDateStr: StringDesc?
        get() = expectedCompletionDate?.convertToLocalDatetime()
}

val List<SubNoteUI>.asExternalModels: List<SubNote>
    get() = map { it.asExternalModel }

val SubNoteUI.asExternalModel: SubNote
    get() = SubNote(
        id = 0,
        text = text,
        completionDate = completionDate,
        expectedCompletionDate = expectedCompletionDate
    )

val SubNote.asUI: SubNoteUI
    get() = SubNoteUI(
        id = id.toString(),
        text = text,
        completionDate = completionDate,
        expectedCompletionDate = expectedCompletionDate
    )

val List<SubNote>.asUI: List<SubNoteUI>
    get() = map { it.asUI }