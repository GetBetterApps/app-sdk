package com.velkonost.getbetter.shared.core.model.note

import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import kotlinx.serialization.Serializable

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
    val area: Area,

    val allowEdit: Boolean
) {
    val createdDateStr: String
        get() = createdDate.convertToLocalDatetime()

    val completionDateStr: String?
        get() = completionDate?.convertToLocalDatetime()

    val expectedCompletionDateStr: String?
        get() = expectedCompletionDate?.convertToLocalDatetime()
}