package com.velkonost.getbetter.shared.features.diary.model

import com.velkonost.getbetter.shared.core.util.randomUUID
import com.velkonost.getbetter.shared.features.notes.api.model.SubNote

data class SubNoteUI(
    val id: String = randomUUID(),
    val text: String = ""
)

val List<SubNoteUI>.asExternalModels: List<SubNote>
    get() = map { it.asExternalModel }

val SubNoteUI.asExternalModel: SubNote
    get() = SubNote(
        id = 0,
        text = text,
        completionDate = 0L
    )