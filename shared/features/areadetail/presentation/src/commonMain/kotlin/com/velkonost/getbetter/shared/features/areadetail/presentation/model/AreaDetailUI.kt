package com.velkonost.getbetter.shared.features.areadetail.presentation.model

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.area.Area
import com.velkonost.getbetter.shared.core.model.area.TermsOfMembership

data class AreaDetailUI(
    val id: Int,
    var name: String,
    val description: String,
    val emoji: Emoji,
//    val members: List<AreaMember>,
    var termsOfMembership: TermsOfMembership,
)

fun Area.toUI() =
    AreaDetailUI(
        id = id,
        name = name,
        description = description,
        emoji = Emoji.getById(emojiId!!),
//        members = membersList,
        termsOfMembership = userTermsOfMembership
    )
