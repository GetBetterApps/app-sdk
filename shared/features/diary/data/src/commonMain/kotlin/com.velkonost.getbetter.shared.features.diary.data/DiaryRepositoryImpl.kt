package com.velkonost.getbetter.shared.features.diary.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.CALENDARS_UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_AREAS_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_CREATE_AREA_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_NOTES_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_TASKS_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.NEW_USER_RESET_DIARY_STATE
import com.velkonost.getbetter.shared.core.datastore.SOCIAL_UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.datastore.UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowLocalRequest
import com.velkonost.getbetter.shared.features.diary.api.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class DiaryRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : DiaryRepository {

    override suspend fun saveUpdatedNoteId(noteId: Int) {
        localDataSource.edit { preferences ->
            preferences[UPDATED_NOTE_ID] = noteId
            preferences[SOCIAL_UPDATED_NOTE_ID] = noteId
            preferences[CALENDARS_UPDATED_NOTE_ID] = noteId
        }
    }

    override suspend fun getUpdatedNoteId(): Flow<ResultState<Int>> = flowLocalRequest {
        val noteId = localDataSource.data.first()[UPDATED_NOTE_ID]
        localDataSource.edit { preferences ->
            preferences.remove(UPDATED_NOTE_ID)
        }

        noteId!!
    }

    override suspend fun checkNeedsResetState(): Boolean {
        val value = localDataSource.data.first()[NEW_USER_RESET_DIARY_STATE] == true
        localDataSource.edit { preferences ->
            preferences[NEW_USER_RESET_DIARY_STATE] = false
        }

        return value
    }

    override suspend fun shouldShowNotesHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_NOTES_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_NOTES_SHOULD_SHOW] = false
        }

        return value
    }

    override suspend fun shouldShowAreasHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_AREAS_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_AREAS_SHOULD_SHOW] = false
        }

        return value
    }

    override suspend fun shouldShowTasksHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_TASKS_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_TASKS_SHOULD_SHOW] = false
        }

        return value
    }

    override suspend fun shouldShowCreateAreaHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_CREATE_AREA_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_CREATE_AREA_SHOULD_SHOW] = false
        }

        return value
    }
}