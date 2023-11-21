package com.velkonost.getbetter.shared.features.calendars.data.remote.model.response

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
    val parentId: String? = null
)