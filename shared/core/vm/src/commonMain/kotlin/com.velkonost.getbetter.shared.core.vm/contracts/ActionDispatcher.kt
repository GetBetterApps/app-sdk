package com.velkonost.getbetter.shared.core.vm.contracts

interface ActionDispatcher<A : UIContract.Action> {
    fun dispatch(action: A)
}