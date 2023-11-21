package com.velkonost.getbetter.shared.core.model.user

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import dev.icerock.moko.resources.desc.StringDesc

data class UserAction(
    val entityType: EntityType,
    val entityId: String,

    val actionType: ActionType,
    val datetime: Long,

    val parentId: String? = null,
    val parentEntityType: EntityType?
) {
    val datetimeStr: StringDesc
        get() = datetime.convertToLocalDatetime()
}