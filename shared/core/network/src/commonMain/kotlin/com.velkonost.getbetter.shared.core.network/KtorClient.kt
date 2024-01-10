package com.velkonost.getbetter.shared.core.network

import com.velkonost.getbetter.shared.core.network.extensions.contentNegotiation
import com.velkonost.getbetter.shared.core.network.extensions.httpTimeout
import io.ktor.client.HttpClient
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

const val KTOR_REQUEST_TIMEOUT_MILLIS = 30_000L

private const val URL = "http://get-better.pro"
const val AVATAR_URL = "$URL/userinfo/getAvatar/"
const val AFFIRMATION_IMAGE_URL = "$URL/affirmations/image/"

internal val KtorClient: HttpClient
    get() {
        val ktorClient = withPlatformEngine {
            Logging {
                logger = Logger.SIMPLE
                level = LogLevel.BODY
            }

            httpTimeout {
                requestTimeoutMillis = KTOR_REQUEST_TIMEOUT_MILLIS
                socketTimeoutMillis = KTOR_REQUEST_TIMEOUT_MILLIS
                connectTimeoutMillis = KTOR_REQUEST_TIMEOUT_MILLIS
            }

            contentNegotiation {
                json(
                    Json {
                        explicitNulls = false
                        ignoreUnknownKeys = true
                        prettyPrint = true
                    }
                )
            }

            defaultRequest {
                url(URL)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }

        return ktorClient
    }