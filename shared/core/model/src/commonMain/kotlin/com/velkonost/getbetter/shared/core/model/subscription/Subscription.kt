package com.velkonost.getbetter.shared.core.model.subscription

data class Subscription(
    val isActive: Boolean,
    val isUnlimited: Boolean,
    val expiredAt: Long,
    val trialUsed: Boolean
) {

    companion object {
        val NoSubscription: Subscription
            get() = Subscription(
                isActive = false,
                isUnlimited = false,
                expiredAt = -1L,
                trialUsed = false
            )
    }

}