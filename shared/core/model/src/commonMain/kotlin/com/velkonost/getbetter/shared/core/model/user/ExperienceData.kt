package com.velkonost.getbetter.shared.core.model.user

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceData(
    val currentLevel: Int,

    val remainExperience: Int,

    val remainExperiencePercent: Float,

    val currentLevelExperienceLimit: Int
) {
    companion object {
        const val LEVEL_LIMIT = 100
    }
}

//60 iz 135 == 60/135 =
val Int.asLevel: Int
    get() = this / ExperienceData.LEVEL_LIMIT

val Int.asExperienceData: ExperienceData
    get() = ExperienceData(
        currentLevel = this.asLevel,
        remainExperience = this - (this.asLevel * ExperienceData.LEVEL_LIMIT),
        currentLevelExperienceLimit = ExperienceData.LEVEL_LIMIT,
        remainExperiencePercent = this / ExperienceData.LEVEL_LIMIT.toFloat()
    )