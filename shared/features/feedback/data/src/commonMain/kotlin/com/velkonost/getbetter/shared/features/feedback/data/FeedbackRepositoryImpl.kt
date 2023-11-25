package com.velkonost.getbetter.shared.features.feedback.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.features.feedback.api.FeedbackRepository
import com.velkonost.getbetter.shared.features.feedback.data.remote.model.FeedbackRemoteDataSource

class FeedbackRepositoryImpl(
    private val remoteDataSource: FeedbackRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : FeedbackRepository