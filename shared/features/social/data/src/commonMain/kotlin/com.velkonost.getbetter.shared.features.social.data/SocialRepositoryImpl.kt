package com.velkonost.getbetter.shared.features.social.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.CALENDARS_UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.datastore.HINT_SOCIAL_ALL_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.NEW_USER_RESET_SOCIAL_STATE
import com.velkonost.getbetter.shared.core.datastore.SOCIAL_UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.datastore.UPDATED_NOTE_ID
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowLocalRequest
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.KtorNote
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.asExternalModel
import com.velkonost.getbetter.shared.features.social.api.SocialRepository
import com.velkonost.getbetter.shared.features.social.data.remote.SocialRemoteDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class SocialRepositoryImpl(
    private val remoteDataSource: SocialRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : SocialRepository {

    override fun fetchAreasFeed(page: Int, pageSize: Int): Flow<ResultState<List<Note>>> =
        flowRequest(
            mapper = { this.map(KtorNote::asExternalModel) },
            request = {
                val token = localDataSource.getUserToken()
                remoteDataSource.getAreasFeed(token, page, pageSize)
            }
        )

    override fun fetchGeneralFeed(page: Int, pageSize: Int): Flow<ResultState<List<Note>>> =
        flowRequest(
            mapper = { this.map(KtorNote::asExternalModel) },
            request = {
                val token = localDataSource.getUserToken()
                remoteDataSource.getGeneralFeed(token, page, pageSize)
            }
        )

    override suspend fun saveUpdatedNoteId(noteId: Int) {
        localDataSource.edit { preferences ->
            preferences[UPDATED_NOTE_ID] = noteId
            preferences[SOCIAL_UPDATED_NOTE_ID] = noteId
            preferences[CALENDARS_UPDATED_NOTE_ID] = noteId
        }
    }

    override suspend fun getUpdatedNoteId(): Flow<ResultState<Int>> = flowLocalRequest {
        val noteId = localDataSource.data.first()[SOCIAL_UPDATED_NOTE_ID]
        localDataSource.edit { preferences ->
            preferences.remove(SOCIAL_UPDATED_NOTE_ID)
        }

        noteId!!
    }

    override suspend fun checkNeedsResetState(): Boolean {
        val value = localDataSource.data.first()[NEW_USER_RESET_SOCIAL_STATE] == true
        localDataSource.edit { preferences ->
            preferences[NEW_USER_RESET_SOCIAL_STATE] = false
        }

        return value
    }

    override suspend fun shouldShowHint(): Boolean {
        val value = localDataSource.data.first()[HINT_SOCIAL_ALL_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_SOCIAL_ALL_SHOULD_SHOW] = false
        }

        return value
    }
}