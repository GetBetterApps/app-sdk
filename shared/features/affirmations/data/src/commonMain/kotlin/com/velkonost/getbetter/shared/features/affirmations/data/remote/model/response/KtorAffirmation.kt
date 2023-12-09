package com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorAffirmation(
    @SerialName("id")
    val id: Int,

    @SerialName("text")
    val text: String
)