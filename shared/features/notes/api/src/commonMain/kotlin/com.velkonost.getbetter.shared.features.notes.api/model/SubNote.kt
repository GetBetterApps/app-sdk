package com.velkonost.getbetter.shared.features.notes.api.model

import kotlinx.serialization.Serializable

@Serializable
data class SubNote(
    val id: Int,
    val text: String,
    val completionDate: Long?,
    val expectedCompletionDate: Long?
)