package com.velkonost.getbetter.shared.core.util

sealed class ResultState<out T> {
    data class Success<out T : Any?>(val data: T) : ResultState<T>()
    data class Failure(val throwable: Throwable? = null) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}

inline fun <T : Any?> ResultState<T>.isLoading(crossinline action: (isLoading: Boolean) -> Unit): ResultState<T> {
    if (this is ResultState.Loading) action(true) else action(false)
    return this
}

inline fun <T : Any?> ResultState<T>.onSuccess(crossinline action: (T?) -> Unit): ResultState<T> {
    if (this is ResultState.Success) action(this.data)
    return this
}

inline fun <T : Any?> ResultState<T>.onFailure(crossinline action: (throwable: Throwable?) -> Unit): ResultState<T> {
    if (this is ResultState.Failure) action(this.throwable)
    return this
}

/**
 * Generic Mapper for providing any class as a Resource
 */
//@Suppress("UNCHECKED_CAST")
//inline fun <reified T, S> T.asResult(mapper: T.() -> S? = { this as? S }): ResultState<S> {
//    return when {
//        this == Unit -> ResultState.Success(null)
//        this !is Throwable -> ResultState.Success(mapper.invoke(this))
//        else -> ResultState.Failure(Throwable((this as? Throwable)?.message ?: "Error"))
//    }
//}