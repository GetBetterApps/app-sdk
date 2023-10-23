package remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import model.Area

@Serializable
data class KtorArea(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("name")
    val name: String = "",

    @SerialName("description")
    val description: String = "",

    @SerialName("isActive")
    val isActive: Boolean = false,

    @SerialName("isPrivate")
    val isPrivate: Boolean = true,

    @SerialName("createdDate")
    val createdDate: Long = 0L,

    @SerialName("imageUrl")
    val imageUrl: String? = null,

    @SerialName("emojiId")
    val emojiId: Int? = null,

    @SerialName("requiredLevel")
    val requiredLevel: Int = 0,

    @SerialName("userTermsOfMembership")
    val userTermsOfMembership: String? = null
)

fun KtorArea.asExternalModel() =
    Area(
        id = id,
        name = name,
        description = description,
        isActive = isActive,
        isPrivate = isPrivate,
        createdDate = createdDate,
        imageUrl = imageUrl,
        emojiId = emojiId,
        requiredLevel = requiredLevel,
    )