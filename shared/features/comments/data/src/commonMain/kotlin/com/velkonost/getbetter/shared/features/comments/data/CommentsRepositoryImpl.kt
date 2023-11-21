package com.velkonost.getbetter.shared.features.comments.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.comments.api.CommentsRepository
import com.velkonost.getbetter.shared.features.comments.data.remote.CommentsRemoteDataSource
import com.velkonost.getbetter.shared.features.comments.data.remote.model.request.CreateCommentRequest
import com.velkonost.getbetter.shared.features.comments.data.remote.model.request.DeleteCommentRequest
import com.velkonost.getbetter.shared.features.comments.data.remote.model.response.KtorComment
import com.velkonost.getbetter.shared.features.comments.data.remote.model.response.KtorCommentsList
import com.velkonost.getbetter.shared.features.comments.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class CommentsRepositoryImpl
constructor(
    private val remoteDataSource: CommentsRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : CommentsRepository {

    override fun createComment(
        entityType: EntityType,
        entityId: Int,
        commentText: String
    ): Flow<ResultState<List<Comment>>> = flowRequest(
        mapper = KtorCommentsList::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = CreateCommentRequest(
                entityType = entityType.responseName,
                entityId = entityId,
                text = commentText
            )

            remoteDataSource.createComment(token, body)
        }
    )

    override fun deleteComment(commentId: Int): Flow<ResultState<List<Comment>>> = flowRequest(
        mapper = KtorCommentsList::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = DeleteCommentRequest(commentId)

            remoteDataSource.deleteComment(token, body)
        }
    )

    override fun getComments(
        entityType: EntityType,
        entityId: Int
    ): Flow<ResultState<List<Comment>>> = flowRequest(
        mapper = KtorCommentsList::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getComments(token, entityType.responseName, entityId)
        }
    )

    override fun getComment(commentId: Int): Flow<ResultState<Comment>> = flowRequest(
        mapper = KtorComment::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.getComment(token, commentId)
        }
    )
}