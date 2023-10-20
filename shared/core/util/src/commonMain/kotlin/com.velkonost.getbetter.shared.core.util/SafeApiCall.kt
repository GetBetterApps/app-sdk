package com.velkonost.getbetter.shared.core.util

import com.velkonost.getbetter.shared.core.network.model.RemoteResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <reified R : Any> flowRequest(noinline request: suspend () -> R): Flow<ResultState<R>> =
    flow {
        emit(ResultState.Loading)

        runCatching<R> {
//            withNetwork {
            request.invoke()
//            }
        }.onFailure {
            emit(ResultState.Failure(it as Exception))
        }.onSuccess { result ->
            emit(ResultState.Success(data = result))
        }

    }

inline fun <reified ResponseType, ReturnType : Any> flowRequest2(
    crossinline mapper: ResponseType.() -> ReturnType,
    noinline request: suspend () -> RemoteResponse<ResponseType>,
): Flow<ResultState<ReturnType>> =
    flow {
        emit(ResultState.Loading)

        runCatching {
//            withNetwork {
            val result = request.invoke()
            if (result.status.code == 200) {
                emit(ResultState.Success(result.data!!.mapper()))
            } else {
                emit(ResultState.Failure(errorCode = 2))
            }
//            }
        }.onFailure {
            emit(ResultState.Failure(it as Exception))
        }
//            .onSuccess { result ->
//            emit(ResultState.Success(data = result))
//        }

    }