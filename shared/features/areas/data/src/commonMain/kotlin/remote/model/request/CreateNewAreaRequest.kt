package remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CreateNewAreaRequest(
    @SerialName("areaId")
    val areaId: Int,

    @SerialName("areaName")
    val areaName: String,

    @SerialName("areaDescription")
    val areaDescription: String,

    @SerialName("isAreaPrivate")
    val isAreaPrivate: Boolean,

    @SerialName("areaRequiredLevel")
    val areaRequiredLevel: Int,

    @SerialName("areaEmojiId")
    val areaEmojiId: Int,

    @SerialName("areaImageUrl")
    val areaImageUrl: String
)