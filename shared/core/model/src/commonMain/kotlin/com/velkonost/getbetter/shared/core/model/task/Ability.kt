package com.velkonost.getbetter.shared.core.model.task

import com.velkonost.getbetter.shared.core.model.user.ExperienceData
import kotlinx.serialization.Serializable

@Serializable
open class Ability(
    val id: Int?,

    val name: String,

    val description: String,

    var experienceData: ExperienceData,

    var affirmations: List<Affirmation> = emptyList()
)