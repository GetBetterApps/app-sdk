package com.velkonost.getbetter.shared.core.model.task

import com.velkonost.getbetter.shared.core.model.area.Area
import kotlinx.serialization.Serializable

@Serializable
data class TaskUI(
    val id: Int?,

    var area: Area,

    val name: String,

    val whatToDo: String,

    val why: String,

    val recommendedTime: Int?,

    val requiredLevel: Int?,

    var abilities: List<Ability>,

    var isFavorite: Boolean,

    var isNotInteresting: Boolean,

    var isCompleted: Boolean,

    var isFavoriteLoading: Boolean = false
)