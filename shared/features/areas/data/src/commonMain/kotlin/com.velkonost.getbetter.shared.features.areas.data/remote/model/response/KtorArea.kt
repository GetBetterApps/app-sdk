package com.velkonost.getbetter.shared.features.areas.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.LikeType
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.area.TermsOfMembership
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorArea(
    @SerialName("id")
    val id: Int = 0,

    @SerialName("name")
    val name: String = "",

    @SerialName("description")
    val description: String = "",

    @SerialName("isActive")
    val isActive: Boolean = false,

    @SerialName("isPrivate")
    val isPrivate: Boolean = true,

    @SerialName("createdDate")
    val createdDate: Long = 0L,

    @SerialName("imageUrl")
    val imageUrl: String? = null,

    @SerialName("emojiId")
    val emojiId: Int? = null,

    @SerialName("requiredLevel")
    val requiredLevel: Int = 0,

    @SerialName("userTermsOfMembership")
    val userTermsOfMembership: String = TermsOfMembership.Allow.responseName,

    @SerialName("isAllowJoin")
    val isAllowJoin: Boolean = false,

    @SerialName("isAllowDelete")
    val isAllowDelete: Boolean = false,

    @SerialName("isAllowEdit")
    val isAllowEdit: Boolean = false,

    @SerialName("isAllowLeave")
    val isAllowLeave: Boolean = false,

    @SerialName("experience")
    val experience: Int = 0,

    @SerialName("totalLikes")
    val totalLikes: Int,

    @SerialName("userLike")
    val userLike: String
)

fun KtorArea.asExternalModel() =
    Area(
        id = id,
        name = name,
        description = description,
        isActive = isActive,
        isPrivate = isPrivate,
        createdDate = createdDate,
        imageUrl = imageUrl,
        emojiId = emojiId,
        requiredLevel = requiredLevel,
        userTermsOfMembership = TermsOfMembership.values()
            .first { it.responseName == userTermsOfMembership },
        isAllowJoin = isAllowJoin,
        isAllowLeave = isAllowLeave,
        isAllowEdit = isAllowEdit,
        isAllowDelete = isAllowDelete,
        experience = experience,
        totalLikes = totalLikes,
        userLike = LikeType.entries.first { it.responseName == userLike }
    )