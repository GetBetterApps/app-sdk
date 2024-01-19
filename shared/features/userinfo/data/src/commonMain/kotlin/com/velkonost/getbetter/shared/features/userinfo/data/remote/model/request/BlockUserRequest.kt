package com.velkonost.getbetter.shared.features.userinfo.data.remote.model.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlockUserRequest(

    @SerialName("userIdToBlock")
    val userIdToBlock: String

)