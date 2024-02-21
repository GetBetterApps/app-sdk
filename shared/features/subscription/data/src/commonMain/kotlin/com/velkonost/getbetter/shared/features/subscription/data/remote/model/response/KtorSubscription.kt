package com.velkonost.getbetter.shared.features.subscription.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorSubscription(
    @SerialName("isActive")
    val isActive: Boolean,

    @SerialName("isUnlimited")
    val isUnlimited: Boolean,

    @SerialName("expiredAt")
    val expiredAt: Long,

    @SerialName("autoRenewal")
    val autoRenewal: Boolean,

    @SerialName("trialUsed")
    val trialUsed: Boolean
)