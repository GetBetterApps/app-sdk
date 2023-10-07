package model

import com.velkonost.getbetter.shared.core.model.TermsOfMembership
import dev.gitlive.firebase.firestore.DocumentReference
import dev.gitlive.firebase.firestore.DocumentSnapshot
import dev.gitlive.firebase.firestore.Timestamp
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

    @SerialName(authorPropertyName)
    val author: AreaMember,

    @SerialName(imageUrlPropertyName)
    val imageUrl: String? = null,

    @SerialName(emojiIdPropertyName)
    val emojiId: Int? = null,

    @SerialName(requiredLevelPropertyName)
    val requiredLevel: Int,

    @SerialName(usersDataPropertyName)
    val usersData: Map<String, Float>,

    @SerialName(membersListPropertyName)
    val membersList: List<AreaMember>,

    @SerialName(isActivePropertyName)
    val isActive: Boolean,

    @SerialName(isPrivatePropertyName)
    val isPrivate: Boolean,
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

suspend fun DocumentSnapshot.toAreaModel() =
    Area(
        id = get(Area.idPropertyName),
        name = get(Area.namePropertyName),
        description = get(Area.descriptionPropertyName),
        isActive = get(Area.isActivePropertyName),
        createdDate = get<Timestamp>(Area.createdDatePropertyName).seconds,
        imageUrl = get(Area.imageUrlPropertyName),
        emojiId = get(Area.emojiIdPropertyName),
        requiredLevel = get(Area.requiredLevelPropertyName),
        usersData = get(Area.usersDataPropertyName),
        membersList = get<List<DocumentReference>>(Area.membersListPropertyName).map {
            it.get().toAreaMember()
        },
        author = get<DocumentReference>(Area.authorPropertyName).get().toAreaMember(),
        isPrivate = get(Area.isPrivatePropertyName)
    )

fun Area.getUserTermsOfMembership(uid: String) =
    if (membersList.any { it.userId == uid }) {
        TermsOfMembership.AlreadyJoined
    } else {
        TermsOfMembership.Allow
    }