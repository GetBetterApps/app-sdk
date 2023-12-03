package com.velkonost.getbetter.shared.core.model.task

import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val id: Int?,

    val name: String,

    val description: String,

    var experience: Long?
)