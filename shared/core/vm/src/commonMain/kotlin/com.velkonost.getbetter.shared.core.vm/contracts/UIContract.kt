package com.velkonost.getbetter.shared.core.vm.contracts

import com.velkonost.getbetter.shared.core.util.randomUUID
import com.velkonost.getbetter.shared.core.vm.navigation.NavigationEvent

sealed interface UIContract {
    interface Action : UIContract

    interface Event : UIContract {
        val id: String
            get() = randomUUID()
    }

    interface Navigation : UIContract {
        val event: NavigationEvent

        val delay: Long
            get() = 0
    }

    interface State : UIContract
}