package remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorSubNote(
    @SerialName("id")
    val id: Int?,

    @SerialName("text")
    val text: String?,

    @SerialName("completionDate")
    val completionDate: Long?
)