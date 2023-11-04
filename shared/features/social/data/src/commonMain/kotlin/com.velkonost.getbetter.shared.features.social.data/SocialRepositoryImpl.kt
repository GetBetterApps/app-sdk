package com.velkonost.getbetter.shared.features.social.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.features.notes.data.remote.NotesRemoteDataSource
import com.velkonost.getbetter.shared.features.social.api.SocialRepository

class SocialRepositoryImpl
constructor(
    private val remoteDataSource: NotesRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : SocialRepository