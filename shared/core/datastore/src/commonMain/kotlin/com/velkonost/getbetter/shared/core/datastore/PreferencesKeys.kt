package com.velkonost.getbetter.shared.core.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey

// HINTS
val HINT_SOCIAL_ALL_SHOULD_SHOW = booleanPreferencesKey("hint_social_feed_should_show")
val HINT_NOTE_COMMENTS_SHOULD_SHOW = booleanPreferencesKey("hint_note_comment_should_show")
val HINT_DIARY_NOTES_SHOULD_SHOW = booleanPreferencesKey("hint_diary_notes_should_show")
val HINT_DIARY_CREATE_NOTE_SHOULD_SHOW = booleanPreferencesKey("hint_diary_create_note_should_show")
val HINT_DIARY_CREATE_GOAL_SHOULD_SHOW = booleanPreferencesKey("hint_diary_create_goal_should_show")
val HINT_DIARY_NOTE_DETAIL_SHOULD_SHOW = booleanPreferencesKey("hint_diary_note_detail_should_show")
val HINT_DIARY_GOAL_DETAIL_SHOULD_SHOW = booleanPreferencesKey("hint_diary_goal_detail_should_show")
val HINT_DIARY_AREAS_SHOULD_SHOW = booleanPreferencesKey("hint_diary_areas_should_show")
val HINT_DIARY_CREATE_AREA_SHOULD_SHOW = booleanPreferencesKey("hint_diary_create_area_should_show")
val HINT_DIARY_ADD_AREA_SHOULD_SHOW = booleanPreferencesKey("hint_diary_add_area_should_show")
val HINT_DIARY_AREA_DETAIL_SHOULD_SHOW = booleanPreferencesKey("hint_diary_area_detail_should_show")
val HINT_DIARY_TASKS_SHOULD_SHOW = booleanPreferencesKey("hint_diary_tasks_should_show")
val HINT_DIARY_TASK_DETAIL_SHOULD_SHOW = booleanPreferencesKey("hint_diary_task_detail_should_show")
val HINT_CALENDARS_SHOULD_SHOW = booleanPreferencesKey("hint_calendars_should_show")
val HINT_ABILITIES_SHOULD_SHOW = booleanPreferencesKey("hint_abilities_should_show")

// OTHER
val ONBOARDING_SHOWN = booleanPreferencesKey("onboarding_shown")

val SELECTED_UI_MODE = stringPreferencesKey("selected_ui_mode")

val TOKEN_KEY = stringPreferencesKey("auth_token")
val ALLOW_SUBSCRIPTION = booleanPreferencesKey("allow_subscription")

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