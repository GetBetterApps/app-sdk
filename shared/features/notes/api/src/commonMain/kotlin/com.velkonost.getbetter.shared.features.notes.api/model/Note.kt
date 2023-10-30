package com.velkonost.getbetter.shared.features.notes.api.model

import com.velkonost.getbetter.shared.core.model.NoteType
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import kotlinx.serialization.Serializable
import model.Area

@Serializable
data class Note(
    val id: Int,
    val noteType: NoteType,
    val text: String,

    val authorId: String,

    val createdDate: Long,
    val completionDate: Long?,
    val expectedCompletionDate: Long?,

    val mediaUrls: List<String>,
    val tags: List<String>,

    val isActive: Boolean,
    val isPrivate: Boolean,

    val subNotes: List<SubNote>,
    val area: Area
) {
    val createdDateStr: String
        get() = createdDate.convertToLocalDatetime()
}