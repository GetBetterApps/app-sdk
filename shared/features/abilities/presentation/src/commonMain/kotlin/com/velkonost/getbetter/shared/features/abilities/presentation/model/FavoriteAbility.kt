package com.velkonost.getbetter.shared.features.abilities.presentation.model

import com.velkonost.getbetter.shared.core.model.task.Ability
import com.velkonost.getbetter.shared.core.model.user.ExperienceData

const val FAVORITE_ID: Int = -1

data object FavoriteAbility : Ability(
    id = FAVORITE_ID,
    name = "",
    description = "",
    experienceData = ExperienceData.EMPTY_ITEM,
)