package com.velkonost.getbetter.shared.core.util

import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <reified R : Any> flowRequest(noinline request: suspend () -> R): Flow<ResultState<R>> =
    flow {
        emit(ResultState.Loading)

        runCatching<R> {
            request.invoke()
        }.onFailure {
            emit(ResultState.Failure(it as Exception))
        }.onSuccess { result ->
            emit(ResultState.Success(data = result))
        }

    }

inline fun <reified ResponseType, ReturnType : Any> flowRequest(
    crossinline mapper: ResponseType.() -> ReturnType,
    noinline onSuccess: (suspend (ReturnType) -> Unit)? = null,
    noinline request: suspend () -> RemoteResponse<ResponseType>,
): Flow<ResultState<ReturnType>> =
    flow {
        emit(ResultState.Loading)

        runCatching {
            val result = request.invoke()

            if (result.status.code == 200) {
                val data = result.data!!.mapper()

                onSuccess?.invoke(data)
                emit(ResultState.Success(data))
            } else {
                emit(ResultState.Failure(errorCode = result.status.code))
            }
        }.onFailure {
            emit(ResultState.Failure(it as Exception))
        }

    }