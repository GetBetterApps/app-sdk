package com.velkonost.getbetter.shared.core.model.note

import kotlinx.serialization.Serializable

@Serializable
data class NoteAuthor(
    val userId: String,
    val displayName: String,
    val avatarUrl: String
)