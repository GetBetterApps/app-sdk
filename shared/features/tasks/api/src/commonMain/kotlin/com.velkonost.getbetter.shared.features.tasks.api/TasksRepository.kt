package com.velkonost.getbetter.shared.features.tasks.api

import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getCompletedTasks(): Flow<ResultState<List<TaskUI>>>

    fun getCurrentList(
        forceUpdate: Boolean = false
    ): Flow<ResultState<List<TaskUI>>>

    fun getTaskDetails(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

    fun addToFavorite(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

    fun removeFromFavorite(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

    fun addToNotInteresting(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

    fun removeFromNotInteresting(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

    fun addToCompleted(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

    fun removeFromCompleted(
        taskId: Int
    ): Flow<ResultState<TaskUI>>

}