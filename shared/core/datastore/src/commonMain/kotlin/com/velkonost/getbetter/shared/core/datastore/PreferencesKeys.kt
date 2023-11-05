package com.velkonost.getbetter.shared.core.datastore

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

val TOKEN_KEY = stringPreferencesKey("auth_token")

val UPDATED_NOTE_ID = intPreferencesKey("updated_note_id")

val SOCIAL_UPDATED_NOTE_ID = intPreferencesKey("social_updated_note_id")

val KEYS_TO_REFRESH = listOf(
    UPDATED_NOTE_ID,
    SOCIAL_UPDATED_NOTE_ID
)