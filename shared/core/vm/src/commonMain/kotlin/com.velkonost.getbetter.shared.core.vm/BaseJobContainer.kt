package com.velkonost.getbetter.shared.core.vm

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext


interface BaseJobContainer {

    var coroutineScope: CoroutineScope
    val error: MutableStateFlow<Throwable?>

    fun launchJob(
        exceptionBlock: ((Throwable) -> Unit)? = null,
        block: suspend () -> Unit
    ): Job {
        return coroutineScope.launch(
            BaseCoroutineExceptionHandler(error, false, exceptionBlock)
        ) {
            block.invoke()
        }
    }

    private class BaseCoroutineExceptionHandler(
        private val errorData: MutableStateFlow<Throwable?>,
        private val postToView: Boolean,
        private val callback: ((Throwable) -> Unit)?
    ) : AbstractCoroutineContextElement(CoroutineExceptionHandler), CoroutineExceptionHandler {

        override fun handleException(context: CoroutineContext, exception: Throwable) {
            exception.message?.let {
//                Logger.log(
//                    severity = Severity.Error,
//                    tag = "TAG",
//                    message = it,
//                    throwable = exception
//                )
            }

            callback?.invoke(exception)
            if (postToView) {
                errorData.tryEmit(exception)
            }
        }
    }

}