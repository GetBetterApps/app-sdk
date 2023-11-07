package com.velkonost.getbetter.shared.features.comments.api.model

import com.velkonost.getbetter.shared.core.model.user.UserInfoShort

data class Comment(
    val text: String,
    val createdDate: Long,
    val allowEdit: Boolean = false,
    val author: UserInfoShort
)