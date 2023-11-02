package com.velkonost.getbetter.shared.features.notes.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.model.note.NoteType
import com.velkonost.getbetter.shared.features.areas.data.remote.model.response.KtorArea
import com.velkonost.getbetter.shared.features.areas.data.remote.model.response.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorNote(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("text")
    val text: String = "",

    @SerialName("noteType")
    val noteType: String?,

    @SerialName("authorId")
    val authorId: String = "",

    @SerialName("createdDate")
    val createdDate: Long = 0L,

    @SerialName("completionDate")
    val completionDate: Long?,

    @SerialName("expectedCompletionDate")
    val expectedCompletionDate: Long?,

    @SerialName("media")
    val media: List<ByteArray>,

    @SerialName("tags")
    val tags: List<String> = emptyList(),

    @SerialName("isActive")
    val isActive: Boolean,

    @SerialName("isPrivate")
    val isPrivate: Boolean,

    @SerialName("area")
    val ktorArea: KtorArea,

    @SerialName("subNotes")
    val subNotes: List<KtorSubNote> = emptyList()
)

fun KtorNote.asExternalModel() =
    Note(
        id = id,
        noteType = NoteType.values().first { it.responseName == noteType },
        text = text,
        authorId = authorId,
        createdDate = createdDate,
        completionDate = completionDate,
        expectedCompletionDate = expectedCompletionDate,
        mediaUrls = emptyList(),
        tags = tags,
        isActive = isActive,
        isPrivate = isPrivate,
        subNotes = subNotes.asExternalModel(),
        area = ktorArea.asExternalModel(),
    )