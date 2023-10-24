package remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorNote(
    @SerialName("id")
    val id: Int?,

    @SerialName("text")
    val text: String?,

    @SerialName("noteType")
    val noteType: String?,

    @SerialName("authorId")
    val authorId: String?,

    @SerialName("createdDate")
    val createdDate: Long?,

    @SerialName("completionDate")
    val completionDate: Long?,

    @SerialName("media")
    val media: List<ByteArray>,

    @SerialName("tags")
    val tags: List<String>?,

    @SerialName("isActive")
    val isActive: Boolean,

    @SerialName("isPrivate")
    val isPrivate: Boolean,

    @SerialName("areaId")
    val areaId: Int?,

    @SerialName("subNotes")
    val subNotes: List<KtorSubNote>?
)