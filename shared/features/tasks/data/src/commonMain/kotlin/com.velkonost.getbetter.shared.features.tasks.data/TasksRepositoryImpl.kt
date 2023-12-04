package com.velkonost.getbetter.shared.features.tasks.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.velkonost.getbetter.shared.core.datastore.extension.getUserToken
import com.velkonost.getbetter.shared.core.model.task.TaskUI
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.util.flowRequest
import com.velkonost.getbetter.shared.features.tasks.api.TasksRepository
import com.velkonost.getbetter.shared.features.tasks.data.remote.TasksRemoteDataSource
import com.velkonost.getbetter.shared.features.tasks.data.remote.model.request.UpdateTaskStateRequest
import com.velkonost.getbetter.shared.features.tasks.data.remote.model.response.KtorTask
import com.velkonost.getbetter.shared.features.tasks.data.remote.model.response.asExternalModel
import kotlinx.coroutines.flow.Flow

class TasksRepositoryImpl
constructor(
    private val remoteDataSource: TasksRemoteDataSource,
    private val localDataSource: DataStore<Preferences>
) : TasksRepository {

    override fun getCompletedTasks(): Flow<ResultState<List<TaskUI>>> = flowRequest(
        mapper = { map { it.asExternalModel() } },
        request = {
            val token = localDataSource.getUserToken()
            remoteDataSource.fetchCompletedTasks(token)
        }
    )

    override fun getCurrentList(forceUpdate: Boolean): Flow<ResultState<List<TaskUI>>> =
        flowRequest(
            mapper = { map { it.asExternalModel() } },
            request = {
                val token = localDataSource.getUserToken()
                remoteDataSource.fetchCurrentList(token, forceUpdate)
            }
        )

    override fun addToFavorite(taskId: Int): Flow<ResultState<TaskUI>> = flowRequest(
        mapper = KtorTask::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateTaskStateRequest(taskId)
            remoteDataSource.addToFavorite(token, body)
        }
    )

    override fun removeFromFavorite(taskId: Int): Flow<ResultState<TaskUI>> = flowRequest(
        mapper = KtorTask::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateTaskStateRequest(taskId)
            remoteDataSource.remoteFromFavorite(token, body)
        }
    )

    override fun addToNotInteresting(taskId: Int): Flow<ResultState<TaskUI>> = flowRequest(
        mapper = KtorTask::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateTaskStateRequest(taskId)
            remoteDataSource.addToNotInteresting(token, body)
        }
    )

    override fun removeFromNotInteresting(taskId: Int): Flow<ResultState<TaskUI>> = flowRequest(
        mapper = KtorTask::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateTaskStateRequest(taskId)
            remoteDataSource.removeFromNotInteresting(token, body)
        }
    )

    override fun addToCompleted(taskId: Int): Flow<ResultState<TaskUI>> = flowRequest(
        mapper = KtorTask::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateTaskStateRequest(taskId)
            remoteDataSource.addToCompleted(token, body)
        }
    )

    override fun removeFromCompleted(taskId: Int): Flow<ResultState<TaskUI>> = flowRequest(
        mapper = KtorTask::asExternalModel,
        request = {
            val token = localDataSource.getUserToken()
            val body = UpdateTaskStateRequest(taskId)
            remoteDataSource.removeFromCompleted(token, body)
        }
    )

}