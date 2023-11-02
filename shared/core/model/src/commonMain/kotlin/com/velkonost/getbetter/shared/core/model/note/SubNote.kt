package com.velkonost.getbetter.shared.core.model.note

import kotlinx.serialization.Serializable

@Serializable
data class SubNote(
    val id: Int,
    val text: String,
    val completionDate: Long?,
    val expectedCompletionDate: Long?
)