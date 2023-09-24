package com.velkonost.getbetter.shared.features.diary

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.diary.contracts.CalendarsAction
import com.velkonost.getbetter.shared.features.diary.contracts.CalendarsNavigation
import com.velkonost.getbetter.shared.features.diary.contracts.CalendarsViewState

class CalendarsViewModel
internal constructor(

) : BaseViewModel<CalendarsViewState, CalendarsAction, CalendarsNavigation, Nothing>(
    initialState = CalendarsViewState()
) {
    override fun dispatch(action: CalendarsAction) = when(action) {

        else -> {}
    }

}