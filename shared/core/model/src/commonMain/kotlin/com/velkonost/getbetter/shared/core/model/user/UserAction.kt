package com.velkonost.getbetter.shared.core.model.user

import com.velkonost.getbetter.shared.core.model.EntityType

data class UserAction(
    val entityType: EntityType,
    val entityId: String,

    val actionType: ActionType,
    val datetime: Long,

    val parentId: String? = null,
    val parentEntityType: EntityType?
)