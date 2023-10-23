package com.velkonost.getbetter.shared.core.network.extensions

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.path

inline fun <reified T> HttpRequestBuilder.makeRequest(
    path: String,
    token: String? = null,
    body: T? = null
) =
    url {
        token?.let {
            header("Authorization", token)
        }

        path(path)

        body?.let {
            setBody(body)
        }
    }

inline fun HttpRequestBuilder.makeRequest(
    path: String,
    token: String? = null,
) =
    url {
        token?.let {
            header("Authorization", token)
        }

        path(path)
    }

inline fun <reified T> HttpRequestBuilder.makeRequest(
    path: String,
    token: String? = null,
    params: Map<String, T>
) =
    url {
        token?.let {
            header("Authorization", token)
        }

        path(path)

        params.forEach {
            parameter(it.key, it.value)
        }
    }