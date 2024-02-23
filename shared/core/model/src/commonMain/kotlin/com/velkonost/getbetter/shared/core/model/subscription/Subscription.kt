package com.velkonost.getbetter.shared.core.model.subscription

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetimeWithoutRelation
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc

data class Subscription(
    val isActive: Boolean,
    val isUnlimited: Boolean,
    val expiredAt: Long,
    val trialUsed: Boolean,
    val autoRenewal: Boolean,
) {

//    val expirationText: StringDesc
//        get() = if (isUnlimited) StringDesc.Resource(SharedR.strings.sub)

    val cancelSubscriptionText: StringDesc
        get() = StringDesc.ResourceFormatted(
            SharedR.strings.subscription_cancel_autorenew_text,
            expiredAt.convertToLocalDatetimeWithoutRelation()
        )

    val cancelAutoRenewEnable: Boolean
        get() = isActive && autoRenewal && isUnlimited

    companion object {
        val NoSubscription: Subscription
            get() = Subscription(
                isActive = false,
                isUnlimited = false,
                expiredAt = -1L,
                trialUsed = false,
                autoRenewal = false
            )
    }

}