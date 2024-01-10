package com.velkonost.getbetter.shared.core.vm

import com.rickclephas.kmm.viewmodel.KMMViewModel
import com.rickclephas.kmm.viewmodel.MutableStateFlow
import com.rickclephas.kmm.viewmodel.coroutineScope
import com.rickclephas.kmm.viewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.velkonost.getbetter.shared.core.model.hint.UIHint
import com.velkonost.getbetter.shared.core.util.ResultState
import com.velkonost.getbetter.shared.core.vm.contracts.ActionDispatcher
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import com.velkonost.getbetter.shared.core.vm.extension.onFailureWithMsg
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent
import com.velkonost.getbetter.shared.core.vm.navigation.RouteNavigator
import com.velkonost.getbetter.shared.core.vm.resource.Message
import com.velkonost.getbetter.shared.core.vm.resource.MessageDeque
import com.velkonost.getbetter.shared.core.vm.resource.MessageType
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel
<S : UIContract.State, A : UIContract.Action, N : UIContract.Navigation, E : UIContract.Event>(
    val savedStateHandle: SavedStateHandle = SavedStateHandle(),
    val initialState: S
) : KMMViewModel(), ActionDispatcher<A>, RouteNavigator, BaseJobContainer {

    override var coroutineScope = vmScope
    override val error = kotlinx.coroutines.flow.MutableStateFlow<Throwable?>(null)

    private val vmScope
        get() = viewModelScope.coroutineScope

    private val _viewState = MutableStateFlow(viewModelScope, initialState)

    @NativeCoroutines
    val viewState
        get() = _viewState.asStateFlow()

    //    @NativeCoroutinesIgnore
    private val _events by lazy { MutableSharedFlow<E>() }

    @NativeCoroutines
    val events by lazy { _events.asSharedFlow() }

    //    @NativeCoroutinesIgnore
    private val _navigationEvent = MutableSharedFlow<NavigationEvent>()

    @NativeCoroutines
    override val navigationEvent: SharedFlow<NavigationEvent>
        get() = _navigationEvent.asSharedFlow()

    open fun init() {

    }

    protected fun <T : N> emit(navigation: T) {
        vmScope.launch {
            delay(navigation.delay)
            _navigationEvent.emit(navigation.event)
        }
    }

    protected fun emit(event: E) {
        vmScope.launch { _events.emit(event) }
    }

    protected fun emit(newState: S) {
        _viewState.update { newState }
    }

    protected fun emit(message: Message) {
        vmScope.launch { MessageDeque.enqueue(message) }
    }

    protected fun UIHint.send(isMain: Boolean = true) {
        val message = Message.Builder()
            .id(this.name)
            .messageType(
                MessageType.Sheet.Builder()
                    .title(this.title)
                    .text(this.text)
                    .type(
                        if (isMain) MessageType.Sheet.Type.Main
                        else MessageType.Sheet.Type.Secondary
                    )
                    .build()
            )
            .build()
        emit(message)
    }

    protected fun <T> Flow<T>.stateInWhileSubscribed(
        started: SharingStarted = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5_000),
        initialValue: T
    ) = stateIn(viewModelScope = viewModelScope, started = started, initialValue = initialValue)

    protected suspend inline infix fun <T> Flow<ResultState<T>>.collectAndProcess(
        crossinline resultProcessing: ResultState<T>.() -> Unit,
    ) {

        collect { result ->
            with(result) {
                resultProcessing.invoke(this)

                onFailureWithMsg { _, message ->
                    message?.let { emit(it) }
                }
            }
        }
    }

}