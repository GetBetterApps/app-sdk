package com.velkonost.getbetter.shared.core.util

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
            emit(ResultState.Success(result))
        }

    }