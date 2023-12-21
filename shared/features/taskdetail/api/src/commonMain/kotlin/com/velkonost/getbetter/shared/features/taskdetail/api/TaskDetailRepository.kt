package com.velkonost.getbetter.shared.features.taskdetail.api

interface TaskDetailRepository {

    suspend fun shouldShowHint(): Boolean

}