package remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateSubNoteStateRequest(
    @SerialName("noteId")
    val noteId: Int,

    @SerialName("subNoteId")
    val subNoteId: Int
)