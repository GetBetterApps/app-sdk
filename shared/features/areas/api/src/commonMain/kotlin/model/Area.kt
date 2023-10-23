package model

import com.velkonost.getbetter.shared.core.model.TermsOfMembership
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Area(

    @SerialName(idPropertyName)
    val id: Int,

    @SerialName(namePropertyName)
    val name: String,

    @SerialName(descriptionPropertyName)
    val description: String,

    @SerialName(createdDatePropertyName)
    val createdDate: Long,

//    @SerialName(authorPropertyName)
//    val author: AreaMember,

    @SerialName(imageUrlPropertyName)
    val imageUrl: String? = null,

    @SerialName(emojiIdPropertyName)
    val emojiId: Int? = null,

    @SerialName(requiredLevelPropertyName)
    val requiredLevel: Int,

//    @SerialName(usersDataPropertyName)
//    val usersData: Map<String, Float>,

//    @SerialName(membersListPropertyName)
//    val membersList: List<AreaMember>,

    @SerialName(isActivePropertyName)
    val isActive: Boolean,

    @SerialName(isPrivatePropertyName)
    val isPrivate: Boolean,

    @SerialName("userTermsOfMembership")
    val userTermsOfMembership: TermsOfMembership
) {
    companion object {
        const val idPropertyName = "id"
        const val namePropertyName = "name"
        const val descriptionPropertyName = "description"
        const val createdDatePropertyName = "createdDate"
        const val authorPropertyName = "author"
        const val imageUrlPropertyName = "imageUrl"
        const val emojiIdPropertyName = "emojiId"
        const val requiredLevelPropertyName = "requiredLevel"
        const val usersDataPropertyName = "usersData"
        const val isActivePropertyName = "isActive"
        const val membersListPropertyName = "membersList"
        const val isPrivatePropertyName = "isPrivate"
    }
}
