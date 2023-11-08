package com.velkonost.getbetter.shared.features.likes.api

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.model.LikeType
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.features.likes.api.model.EntityLikes
import kotlinx.coroutines.flow.Flow

interface LikesRepository {

    fun addLike(
        entityType: EntityType,
        entityId: Int,
        likeType: LikeType
    ): Flow<ResultState<EntityLikes>>

}