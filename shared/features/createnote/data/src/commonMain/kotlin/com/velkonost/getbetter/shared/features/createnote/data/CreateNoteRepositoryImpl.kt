package com.velkonost.getbetter.shared.features.createnote.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.velkonost.getbetter.shared.core.datastore.HINT_ABILITIES_SHOULD_SHOW
import com.velkonost.getbetter.shared.core.datastore.HINT_DIARY_CREATE_NOTE_SHOULD_SHOW
import com.velkonost.getbetter.shared.features.createnote.api.CreateNoteRepository
import kotlinx.coroutines.flow.first

class CreateNoteRepositoryImpl(
    private val localDataSource: DataStore<Preferences>
) : CreateNoteRepository {

    override suspend fun shouldShowHint(): Boolean {
        val value = localDataSource.data.first()[HINT_DIARY_CREATE_NOTE_SHOULD_SHOW] != false
        localDataSource.edit { preferences ->
            preferences[HINT_DIARY_CREATE_NOTE_SHOULD_SHOW] = false
        }

        return value
    }

}