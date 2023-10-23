package remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorArea(
    @SerialName("id")
    val id: Int? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("description")
    val description: String? = null,

    @SerialName("isActive")
    val isActive: Boolean = false,

    @SerialName("isPrivate")
    val isPrivate: Boolean = true,

    @SerialName("createdDate")
    val createdDate: Long? = null,

    @SerialName("imageUrl")
    val imageUrl: String? = null,

    @SerialName("emojiId")
    val emojiId: Int? = null,

    @SerialName("requiredLevel")
    val requiredLevel: Int? = null,

    @SerialName("userTermsOfMembership")
    val userTermsOfMembership: String? = null
)