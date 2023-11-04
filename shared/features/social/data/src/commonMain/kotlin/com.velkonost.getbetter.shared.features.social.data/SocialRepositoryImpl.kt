package com.velkonost.getbetter.shared.features.social.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.note.Note
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.KtorNote
import com.velkonost.getbetter.shared.features.notes.data.remote.model.response.asExternalModel
import com.velkonost.getbetter.shared.features.social.api.SocialRepository
import com.velkonost.getbetter.shared.features.social.data.remote.SocialRemoteDataSource
import kotlinx.coroutines.flow.Flow

class SocialRepositoryImpl
constructor(
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

}