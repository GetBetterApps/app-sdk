package com.velkonost.getbetter.shared.features.tasks.api

import com.velkonost.getbetter.shared.core.model.task.Task
import com.velkonost.getbetter.shared.core.util.ResultState
import kotlinx.coroutines.flow.Flow

interface TasksRepository {

    fun getCompletedTasks(): Flow<ResultState<List<Task>>>

    fun getCurrentList(
        forceUpdate: Boolean = false
    ): Flow<ResultState<List<Task>>>

    fun addToFavorite(
        taskId: Int
    ): Flow<ResultState<Task>>

    fun removeFromFavorite(
        taskId: Int
    ): Flow<ResultState<Task>>

    fun addToNotInteresting(
        taskId: Int
    ): Flow<ResultState<Task>>

    fun removeFromNotInteresting(
        taskId: Int
    ): Flow<ResultState<Task>>

    fun addToCompleted(
        taskId: Int
    ): Flow<ResultState<Task>>

    fun removeFromCompleted(
        taskId: Int
    ): Flow<ResultState<Task>>

}