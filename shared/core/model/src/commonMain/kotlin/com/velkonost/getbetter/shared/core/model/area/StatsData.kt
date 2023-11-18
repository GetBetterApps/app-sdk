package com.velkonost.getbetter.shared.core.model.area

import kotlinx.serialization.Serializable

@Serializable
data class StatsData(
    val notesAmount: Int,
    val membersAmount: Int
)