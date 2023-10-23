package com.velkonost.getbetter.shared.features.addarea.presentation.model

import com.velkonost.getbetter.shared.core.model.Emoji
import com.velkonost.getbetter.shared.core.model.TermsOfMembership
import dev.icerock.moko.resources.ImageResource
import model.Area

data class AreaUI(
    val id: Int,
    var name: String,
    val description: String,
    val emojiRes: ImageResource,
//    val members: List<AreaMember>,
    var termsOfMembership: TermsOfMembership,
    var isLoading: Boolean = false
)

fun Area.toUI() =
    AreaUI(
        id = id,
        name = name,
        description = description,
        emojiRes = Emoji.getIconById(emojiId!!),
//        members = membersList,
        termsOfMembership = userTermsOfMembership
    )

