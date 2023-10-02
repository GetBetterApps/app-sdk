package model

import com.velkonost.getbetter.shared.features.userinfo.api.model.UserInfo
import dev.gitlive.firebase.firestore.DocumentSnapshot
import kotlinx.serialization.Serializable

@Serializable
data class AreaMember(
    val userId: String,
    val displayName: String,
    val avatarUrl: String
)

fun DocumentSnapshot.toAreaMember() =
    AreaMember(
        userId = this.id,
        displayName =
        if (contains(UserInfo.displayNamePropertyName))
            get(UserInfo.displayNamePropertyName)
        else "",
        avatarUrl =
        if (contains(UserInfo.avatarUrlPropertyName))
            get(UserInfo.avatarUrlPropertyName)
        else ""
    )