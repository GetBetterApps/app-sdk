package com.velkonost.getbetter.shared.features.subscription.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorCreateSubscription(
    @SerialName("paymentUrl")
    val paymentUrl: String
)