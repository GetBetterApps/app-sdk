package remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdateNoteStateRequest(
    @SerialName("noteId")
    val noteId: Int
)