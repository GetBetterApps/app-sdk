package com.velkonost.getbetter.shared.features.notes.api.model

import com.velkonost.getbetter.shared.core.model.NoteType
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    @SerialName(idPropertyName)
    val id: Int,

    @SerialName(noteTypePropertyName)
    val noteType: NoteType,

    @SerialName(textPropertyName)
    val text: String,

    @SerialName(authorPropertyName)
    val authorId: String,

    @SerialName(createdDatePropertyName)
    val createdDate: Long,

    @SerialName(completionDatePropertyName)
    val completionDate: Long?,

    @SerialName(mediaUrlsPropertyName)
    val mediaUrls: List<String>,

    @SerialName(tagsPropertyName)
    val tags: List<String>,

    @SerialName(isActivePropertyName)
    val isActive: Boolean,

    @SerialName(isPrivatePropertyName)
    val isPrivate: Boolean,

    @SerialName(subNotesPropertyName)
    val subNotes: List<SubNote>,

    val area: Area

) {
    companion object {
        const val idPropertyName = "id"
        const val noteTypePropertyName = "noteType"
        const val textPropertyName = "text"
        const val authorPropertyName = "author"
        const val createdDatePropertyName = "createdData"
        const val completionDatePropertyName = "completionDate"
        const val mediaUrlsPropertyName = "mediaUrls"
        const val tagsPropertyName = "tags"
        const val isActivePropertyName = "isActive"
        const val isPrivatePropertyName = "isPrivate"
        const val subNotesPropertyName = "subNotes"
        const val isCompletedPropertyName = "isCompleted"
    }
}