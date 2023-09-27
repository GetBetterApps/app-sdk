package model

import dev.gitlive.firebase.firestore.DocumentReference
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Area(

    @SerialName(idPropertyName)
    val id: String,

    @SerialName(namePropertyName)
    val name: String,

    @SerialName(descriptionPropertyName)
    val description: String,

    @SerialName(createdDatePropertyName)
    val createdDate: Long,

    @SerialName(authorIdPropertyName)
    val authorId: String,

    @SerialName(imageUrlPropertyName)
    val imageUrl: String? = null,

    @SerialName(emojiIdPropertyName)
    val emojiId: String? = null,

    @SerialName(requiredLevelPropertyName)
    val requiredLevel: Int,

    @SerialName(usersDataPropertyName)
    val usersData: Map<DocumentReference, Float>,

    @SerialName(isActivePropertyName)
    val isActive: Boolean

) {
    companion object {
        const val idPropertyName = "id"
        const val namePropertyName = "name"
        const val descriptionPropertyName = "description"
        const val createdDatePropertyName = "createdDate"
        const val authorIdPropertyName = "authorId"
        const val imageUrlPropertyName = "imageUrl"
        const val emojiIdPropertyName = "emojiId"
        const val requiredLevelPropertyName = "requiredLevel"
        const val usersDataPropertyName = "usersData"
        const val isActivePropertyName = "isActive"
    }
}