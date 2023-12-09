package com.velkonost.getbetter.shared.core.model.task

import kotlinx.serialization.Serializable

@Serializable
data class Affirmation(
    val id: Int,

    val text: String
)