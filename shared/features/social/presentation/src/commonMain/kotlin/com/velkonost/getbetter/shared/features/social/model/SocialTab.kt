package com.velkonost.getbetter.shared.features.social.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class SocialTab(val title: StringDesc) {
    GeneralFeed(title = StringDesc.Resource(SharedR.strings.social_general_feed_title)),
    AreasFeed(title = StringDesc.Resource(SharedR.strings.social_areas_feed_title)),
}