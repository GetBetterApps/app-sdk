package com.velkonost.getbetter.shared.features.comments.api

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.comments.Comment
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface CommentsRepository {

    fun createComment(
        entityType: EntityType, entityId: Int, commentText: String
    ): Flow<ResultState<List<Comment>>>

    fun deleteComment(
        commentId: Int
    ): Flow<ResultState<List<Comment>>>

    fun getComments(
        entityType: EntityType, entityId: Int
    ): Flow<ResultState<List<Comment>>>

    fun getComment(
        commentId: Int
    ): Flow<ResultState<Comment>>
}