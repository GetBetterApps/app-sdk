package com.velkonost.getbetter.shared.features.addarea.presentation.model

import com.velkonost.getbetter.shared.core.model.Emoji
import dev.icerock.moko.resources.ImageResource
import model.Area
import model.AreaMember

data class AreaUI(
    val id: String,
    var name: String,
    val description: String,
    val emojiRes: ImageResource,
    val members: List<AreaMember>,
    var termsOfMembership: TermsOfMembership,
    var isLoading: Boolean = false
)

fun Area.toUI(termsOfMembership: TermsOfMembership) =
    AreaUI(
        id = id,
        name = name,
        description = description,
        emojiRes = Emoji.getIconById(emojiId!!),
        members = membersList,
        termsOfMembership = termsOfMembership
    )

fun Area.getUserTermsOfMembership(uid: String) =
    if (membersList.any { it.userId == uid }) {
        TermsOfMembership.AlreadyJoined
    } else {
        TermsOfMembership.Allow
    }