package com.velkonost.getbetter.shared.features.comments.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.features.comments.api.CommentsRepository
import com.velkonost.getbetter.shared.features.comments.data.remote.CommentsRemoteDataSource

class CommentsRepositoryImpl
constructor(
    private val remoteDataSource: CommentsRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : CommentsRepository