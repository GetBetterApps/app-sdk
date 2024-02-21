package com.velkonost.getbetter.shared.core.model.subscription

data class Subscription(
    val isActive: Boolean,
    val isUnlimited: Boolean,
    val expiredAt: Long,
    val trialUsed: Boolean
)