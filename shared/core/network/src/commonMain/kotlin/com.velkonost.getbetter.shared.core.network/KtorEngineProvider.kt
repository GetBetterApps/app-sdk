package com.velkonost.getbetter.shared.core.network

import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

internal expect fun withPlatformEngine(config: HttpClientConfig<*>.() -> Unit): HttpClient