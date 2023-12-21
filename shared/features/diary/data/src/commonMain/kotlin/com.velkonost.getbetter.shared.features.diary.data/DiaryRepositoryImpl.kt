package com.velkonost.getbetter.shared.features.diary.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.NEW_USER_RESET_DIARY_STATE
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
        TODO("Not yet implemented")
    }

    override suspend fun shouldShowAreasHint(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun shouldShowTasksHint(): Boolean {
        TODO("Not yet implemented")
    }
}