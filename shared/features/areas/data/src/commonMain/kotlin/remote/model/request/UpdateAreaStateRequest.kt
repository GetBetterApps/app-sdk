package remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateAreaStateRequest(
    @SerialName("areaId")
    val areaId: Int
)