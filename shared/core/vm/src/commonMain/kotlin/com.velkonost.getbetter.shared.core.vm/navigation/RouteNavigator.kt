package com.velkonost.getbetter.shared.core.vm.navigation

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.SharedFlow

interface RouteNavigator {
    @NativeCoroutines
    val navigationEvent: SharedFlow<NavigationEvent>
}