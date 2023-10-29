package com.velkonost.getbetter.shared.features.notes.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SubNote(
    @SerialName("id")
    val id: Int,

    @SerialName("text")
    val text: String,

    @SerialName("completionDate")
    val completionDate: Long?
)