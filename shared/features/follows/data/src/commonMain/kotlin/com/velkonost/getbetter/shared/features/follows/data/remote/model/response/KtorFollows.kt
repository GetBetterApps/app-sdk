package com.velkonost.getbetter.shared.features.follows.data.remote.model.response

import com.velkonost.getbetter.shared.features.follows.api.model.FollowsData
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.KtorUserInfoShort
import com.velkonost.getbetter.shared.features.userinfo.data.remote.model.response.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorFollows(
    @SerialName("items")
    val items: List<KtorUserInfoShort>
)

infix fun KtorFollows.asExternalModel(userType: UserType): FollowsData {
    return FollowsData(
        followers = if (userType == UserType.Followers) this.items.map { it.asExternalModel() } else emptyList(),
        follows = if (userType == UserType.Follows) this.items.map { it.asExternalModel() } else emptyList()
    )
}

enum class UserType {
    Followers,
    Follows
}