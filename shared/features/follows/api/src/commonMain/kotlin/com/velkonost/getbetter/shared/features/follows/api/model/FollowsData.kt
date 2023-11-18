package com.velkonost.getbetter.shared.features.follows.api.model

import com.velkonost.getbetter.shared.core.model.user.UserInfoShort

data class FollowsData(
    val followers: List<UserInfoShort> = emptyList(),
    val follows: List<UserInfoShort> = emptyList()
)