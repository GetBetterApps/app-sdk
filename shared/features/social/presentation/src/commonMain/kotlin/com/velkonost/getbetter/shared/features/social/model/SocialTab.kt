package com.velkonost.getbetter.shared.features.social.model

import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class SocialTab(val title: StringDesc) {
    GeneralFeed(title = StringDesc.Resource(SharedR.strings.diary_notes_title)),
    AreasFeed(title = StringDesc.Resource(SharedR.strings.diary_areas_title)),
}