package com.velkonost.getbetter.shared.features.tasks.data.remote

import io.ktor.client.HttpClient

class TasksRemoteDataSource(
    private val httpClient: HttpClient
) {

    suspend fun fetchCompletedTasks(
        token: String?,

        )
}