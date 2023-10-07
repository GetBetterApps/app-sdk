package com.velkonost.getbetter.shared.features.areadetail.presentation.model

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.TermsOfMembership
import model.Area
import model.AreaMember

data class AreaDetailUI(
    val id: String,
    var name: String,
    val description: String,
    val emoji: Emoji,
    val members: List<AreaMember>,
    var termsOfMembership: TermsOfMembership,
)

fun Area.toUI(termsOfMembership: TermsOfMembership) =
    AreaDetailUI(
        id = id,
        name = name,
        description = description,
        emoji = Emoji.getById(emojiId!!),
        members = membersList,
        termsOfMembership = termsOfMembership
    )
