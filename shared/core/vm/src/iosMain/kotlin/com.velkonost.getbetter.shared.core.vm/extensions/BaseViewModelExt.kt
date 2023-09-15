package com.velkonost.getbetter.shared.core.vm.extensions

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

@Suppress("unused") // Used in iOS
fun BaseViewModel<out UIContract.State, out UIContract.Action, out UIContract.Navigation, out UIContract.Event>.put(
    args: Map<String, String>
) =
    args.forEach { (key, value) ->
        savedStateHandle[key] = value
    }
