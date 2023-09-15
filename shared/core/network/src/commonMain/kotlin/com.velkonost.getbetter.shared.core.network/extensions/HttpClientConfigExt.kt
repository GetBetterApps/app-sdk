package com.velkonost.getbetter.shared.core.network.extensions

import io.ktor.client.HttpClientConfig
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.HttpTimeout.HttpTimeoutCapabilityConfiguration
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

internal fun HttpClientConfig<*>.httpTimeout(block: HttpTimeoutCapabilityConfiguration.() -> Unit) =
    install(HttpTimeout, block)

fun HttpClientConfig<*>.contentNegotiation(block: ContentNegotiation.Config.() -> Unit) =
    install(ContentNegotiation, block)