package com.velkonost.getbetter.shared.features.abilities.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.task.Ability
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorAbility(
    @SerialName("id")
    val id: Int?,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("experience")
    var experience: Long?
)

fun KtorAbility.asExternalModel() =
    Ability(
        id = id,
        name = name,
        description = description,
        experience = experience
    )