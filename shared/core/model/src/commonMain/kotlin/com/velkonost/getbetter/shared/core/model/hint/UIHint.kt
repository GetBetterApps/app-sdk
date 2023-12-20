package com.velkonost.getbetter.shared.core.model.hint

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class UIHint(
    val title: StringDesc? = null,
    val text: StringDesc
) {

    SocialAll(
        title = StringDesc.Resource(SharedR.strings.hint_social_all_title),
        text = StringDesc.Resource(SharedR.strings.hint_social_all_text)
    )

}