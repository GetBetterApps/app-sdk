package com.velkonost.getbetter.shared.features.comments.api

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.features.comments.api.model.Comment

interface CommentsRepository {

    fun createComment(
        entityType: EntityType, entityId: Int, commentText: String
    ): List<Comment>

    fun deleteComment(
        commentId: Int
    ): List<Comment>

    fun getComments(
        entityType: EntityType, entityId: Int
    ): List<Comment>
}