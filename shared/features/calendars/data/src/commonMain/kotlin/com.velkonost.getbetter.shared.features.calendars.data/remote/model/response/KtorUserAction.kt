package com.velkonost.getbetter.shared.features.calendars.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.user.ActionType
import com.velkonost.getbetter.shared.core.model.user.UserAction
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorUserAction(
    @SerialName("entityType")
    val entityType: String?,

    @SerialName("entityId")
    val entityId: String?,

    @SerialName("actionType")
    val actionType: String?,

    @SerialName("datetime")
    val datetime: Long?,

    @SerialName("parentId")
    val parentId: String? = null,

    @SerialName("parentEntityType")
    val parentEntityType: String?
)

fun KtorUserAction.asExternalModel() =
    UserAction(
        entityType = EntityType.entries.first { it.responseName == entityType },
        entityId = entityId!!,
        actionType = ActionType.entries.first { it.responseName == actionType },
        datetime = datetime!!,
        parentId = parentId,
        parentEntityType = EntityType.entries.firstOrNull { it.responseName == parentEntityType }
    )