package com.velkonost.getbetter.shared.core.model.user

import kotlinx.serialization.Serializable

@Serializable
data class ExperienceData(
    val currentLevel: Int,

    val remainExperience: Int,

    val currentLevelExperienceLimit: Int
) {
    companion object {
        const val LEVEL_LIMIT = 100
    }
}

val Int.asLevel: Int
    get() = this / ExperienceData.LEVEL_LIMIT

val Int.asExperienceData: ExperienceData
    get() = ExperienceData(
        currentLevel = this.asLevel,
        remainExperience = this - (this.asLevel * ExperienceData.LEVEL_LIMIT),
        currentLevelExperienceLimit = ExperienceData.LEVEL_LIMIT
    )