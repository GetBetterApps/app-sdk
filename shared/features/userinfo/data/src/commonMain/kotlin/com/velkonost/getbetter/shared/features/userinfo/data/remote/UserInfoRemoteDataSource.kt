package com.velkonost.getbetter.shared.features.userinfo.data.remote

import io.ktor.client.HttpClient

class UserInfoRemoteDataSource(
    private val httpClient: HttpClient
)