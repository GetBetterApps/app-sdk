package com.velkonost.getbetter.shared.features.notes.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EditNoteRequest(
    @SerialName("noteId")
    val noteId: Int,

    @SerialName("noteText")
    val noteText: String,

    @SerialName("completionDate")
    val completionDate: Long?,

    @SerialName("expectedCompletionDate")
    val expectedCompletionDate: Long?,

    @SerialName("noteMedia")
    val noteMedia: List<String> = emptyList(),

    @SerialName("noteTags")
    val noteTags: List<String>,

    @SerialName("subNotes")
    val subNotes: List<SubNoteRequestData>?
)