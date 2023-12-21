package com.velkonost.getbetter.shared.features.notedetail.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_GOAL_DETAIL_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_NOTE_DETAIL_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.HINT_NOTE_COMMENTS_SHOULD_SHOW
import com.velkonost.getbetter.shared.features.notedetail.api.NoteDetailRepository
import kotlinx.coroutines.flow.first

class NoteDetailRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : NoteDetailRepository {

    override suspend fun shouldShowCommentsHint(): Boolean {
        val value = localDataSource.data.first()[HINT_NOTE_COMMENTS_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_NOTE_COMMENTS_SHOULD_SHOW] = false
        }

        return value
    }

    override suspend fun shouldShowNoteHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_NOTE_DETAIL_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_NOTE_DETAIL_SHOULD_SHOW] = false
        }

        return value
    }

    override suspend fun shouldShowGoalHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_GOAL_DETAIL_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_GOAL_DETAIL_SHOULD_SHOW] = false
        }

        return value
    }

}