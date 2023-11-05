package com.velkonost.getbetter.shared.features.notes.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNewNoteRequest(
    @SerialName("noteType")
    val noteType: String,

    @SerialName("noteText")
    val noteText: String,

    @SerialName("noteMediaUrls")
    val noteMediaUrls: List<String> = emptyList(),

    @SerialName("noteTags")
    val noteTags: List<String> = emptyList(),

    @SerialName("isNotePrivate")
    val isNotePrivate: Boolean,

    @SerialName("expectedCompletionDate")
    val noteExpectedCompletionDate: Long?,

    @SerialName("areaId")
    val areaId: Int,

    @SerialName("subNotes")
    val subNotes: List<SubNoteRequestData> = emptyList()
)

@Serializable
data class SubNoteRequestData(
    @SerialName("subNoteText")
    val subNoteText: String,

    @SerialName("expectedCompletionDate")
    val expectedCompletionDate: Long?,

    @SerialName("completionDate")
    val completionDate: Long?
)