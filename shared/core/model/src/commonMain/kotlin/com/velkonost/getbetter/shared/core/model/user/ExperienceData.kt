package com.velkonost.getbetter.shared.core.model.user

import com.velkonost.getbetter.shared.core.util.randomUUID
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.serialization.Serializable

@Serializable
data class ExperienceData(
    val id: String = randomUUID(),

    val currentLevel: Int,

    val remainExperience: Int,

    val remainExperiencePercent: Float,

    val currentLevelExperienceLimit: Int
) {
    companion object {
        const val LEVEL_LIMIT = 100
    }

    val currentLevelStr: StringDesc
        get() = StringDesc.ResourceFormatted(SharedR.strings.experience_level, currentLevel)
}

//60 iz 135 == 60/135 =
val Int.asLevel: Int
    get() = this / ExperienceData.LEVEL_LIMIT

val Int.asExperienceData: ExperienceData
    get() = ExperienceData(
        currentLevel = this.asLevel,
        remainExperience = this - (this.asLevel * ExperienceData.LEVEL_LIMIT),
        currentLevelExperienceLimit = ExperienceData.LEVEL_LIMIT,
        remainExperiencePercent = (this - (this.asLevel * ExperienceData.LEVEL_LIMIT)) / ExperienceData.LEVEL_LIMIT.toFloat()
    )