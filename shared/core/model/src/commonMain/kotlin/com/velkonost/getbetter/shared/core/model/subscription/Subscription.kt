package com.velkonost.getbetter.shared.core.model.subscription

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.convertToLocalDatetimeWithoutRelation
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc

data class Subscription(
    val isActive: Boolean,
    val isUnlimited: Boolean,
    val expiredAt: Long,
    val trialUsed: Boolean,
    val autoRenewal: Boolean,
    val fake: Boolean = false
) {

    val expirationText: StringDesc
        get() =
            if (isUnlimited) StringDesc.Resource(SharedR.strings.subscription_text_unlimited)
            else if (autoRenewal) StringDesc.ResourceFormatted(
                SharedR.strings.subscription_text_autorenew_on,
                expiredAt.convertToLocalDatetimeWithoutRelation()
            )
            else StringDesc.ResourceFormatted(
                SharedR.strings.subscription_text_autorenew_off,
                expiredAt.convertToLocalDatetimeWithoutRelation()
            )

    val cancelSubscriptionText: StringDesc
        get() = StringDesc.ResourceFormatted(
            SharedR.strings.subscription_cancel_autorenew_text,
            expiredAt.convertToLocalDatetimeWithoutRelation()
        )

    val cancelAutoRenewEnable: Boolean
        get() = isActive && autoRenewal && !isUnlimited

    companion object {

        val FakeSubscription: Subscription
            get() = Subscription(
                isActive = true,
                isUnlimited = true,
                expiredAt = -1L,
                trialUsed = true,
                autoRenewal = false,
                fake = true
            )

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