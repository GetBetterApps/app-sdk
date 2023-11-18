package com.velkonost.getbetter.shared.features.follows.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.features.follows.api.FollowsRepository
import com.velkonost.getbetter.shared.features.follows.data.remote.FollowsRemoteDataSource

class FollowsRepositoryImpl
constructor(
    private val remoteDataSource: FollowsRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : FollowsRepository