package com.velkonost.getbetter.shared.features.areadetail.presentation.model

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.area.TermsOfMembership
import com.velkonost.getbetter.shared.core.model.likes.LikesData
import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import com.velkonost.getbetter.shared.core.model.user.asExperienceData

data class AreaDetailUI(
    val id: Int,
    var name: String,
    val description: String,
    val emoji: Emoji,
    val isPrivate: Boolean,
    var termsOfMembership: TermsOfMembership,

    var likesData: LikesData,
    var experienceData: ExperienceData
)

fun Area.toUI() =
    AreaDetailUI(
        id = id,
        name = name,
        description = description,
        emoji = Emoji.getById(emojiId!!),
        isPrivate = isPrivate,
        termsOfMembership = userTermsOfMembership,
        likesData = likesData,
        experienceData = userExperience.asExperienceData
    )
