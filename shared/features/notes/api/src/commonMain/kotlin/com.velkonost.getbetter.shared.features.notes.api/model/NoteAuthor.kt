package com.velkonost.getbetter.shared.features.notes.api.model

import kotlinx.serialization.Serializable

@Serializable
data class NoteAuthor(
    val userId: String,
    val displayName: String,
    val avatarUrl: String
)