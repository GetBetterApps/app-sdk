package com.velkonost.getbetter.shared.features.abilities.data.remote.model.response

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.model.user.asExperienceData
import com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response.KtorAffirmation
import com.velkonost.getbetter.shared.features.affirmations.data.remote.model.response.asExternalModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class KtorAbilityDetail(
    @SerialName("id")
    val id: Int?,

    @SerialName("name")
    val name: String,

    @SerialName("description")
    val description: String,

    @SerialName("experience")
    var experience: Long,

    var affirmations: List<KtorAffirmation>
)

fun KtorAbilityDetail.asExternalModel() =
    Ability(
        id = id,
        name = name,
        description = description,
        experienceData = experience.toInt().asExperienceData,
        affirmations = affirmations.map { it.asExternalModel() }
    )