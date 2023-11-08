package com.velkonost.getbetter.shared.core.model.comments

import com.velkonost.getbetter.shared.core.model.user.UserInfoShort
import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetime
import dev.icerock.moko.resources.desc.StringDesc

data class Comment(
    val text: String,
    val createdDate: Long,
    val allowEdit: Boolean = false,
    val author: UserInfoShort
) {
    val createdDateStr: StringDesc
        get() = createdDate.convertToLocalDatetime()
}