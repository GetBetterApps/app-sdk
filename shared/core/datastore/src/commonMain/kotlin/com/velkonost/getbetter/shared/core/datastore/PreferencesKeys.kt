package com.velkonost.getbetter.shared.core.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

val TOKEN_KEY = stringPreferencesKey("auth_token")

val USER_REGISTRATION_MILLIS = longPreferencesKey("user_registration_millis")

val UPDATED_NOTE_ID = intPreferencesKey("updated_note_id")
val SOCIAL_UPDATED_NOTE_ID = intPreferencesKey("social_updated_note_id")
val CALENDARS_UPDATED_NOTE_ID = intPreferencesKey("calendars_updated_note_id")

val NEW_USER_RESET_AUTH_STATE = booleanPreferencesKey("new_user_reset_auth_state")
val NEW_USER_RESET_SOCIAL_STATE = booleanPreferencesKey("new_user_reset_social_state")
val NEW_USER_RESET_DIARY_STATE = booleanPreferencesKey("new_user_reset_diary_state")
val NEW_USER_RESET_CALENDAR_STATE = booleanPreferencesKey("new_user_reset_calendar_state")

val KEYS_RESET_STATE = listOf(
    NEW_USER_RESET_AUTH_STATE,
    NEW_USER_RESET_SOCIAL_STATE,
    NEW_USER_RESET_DIARY_STATE,
    NEW_USER_RESET_CALENDAR_STATE
)

val KEYS_TO_REFRESH = listOf(
    UPDATED_NOTE_ID,
    SOCIAL_UPDATED_NOTE_ID,
).plus(KEYS_RESET_STATE)