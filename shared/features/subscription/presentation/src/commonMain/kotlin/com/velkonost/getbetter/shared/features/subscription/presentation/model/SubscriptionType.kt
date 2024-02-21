package com.velkonost.getbetter.shared.features.subscription.presentation.model

import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.StringDesc

enum class SubscriptionType(
    val title: StringDesc,
    val text: StringDesc,
    val price: StringDesc,
    val responseName: String
) {
    Month(
        title = StringDesc.Resource(SharedR.strings.offer_month_title),
        text = StringDesc.Resource(SharedR.strings.offer_month_text),
        price = StringDesc.Resource(SharedR.strings.offer_month_price),
        responseName = "month"
    ),

    Year(
        title = StringDesc.Resource(SharedR.strings.offer_year_title),
        text = StringDesc.Resource(SharedR.strings.offer_year_text),
        price = StringDesc.Resource(SharedR.strings.offer_year_price),
        responseName = "year"
    ),

    Unlimited(
        title = StringDesc.Resource(SharedR.strings.offer_unlimited_title),
        text = StringDesc.Resource(SharedR.strings.offer_unlimited_text),
        price = StringDesc.Resource(SharedR.strings.offer_unlimited_price),
        responseName = "unlimited"
    )
}