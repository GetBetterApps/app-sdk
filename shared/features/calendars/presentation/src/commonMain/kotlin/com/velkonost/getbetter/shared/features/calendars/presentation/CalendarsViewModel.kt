package com.velkonost.getbetter.shared.features.calendars.presentation

import com.velkonost.getbetter.shared.core.vm.BaseViewModel
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsAction
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsNavigation
import com.velkonost.getbetter.shared.features.calendars.presentation.contracts.CalendarsViewState

class CalendarsViewModel
internal constructor(

) : BaseViewModel<CalendarsViewState, CalendarsAction, CalendarsNavigation, Nothing>(
    initialState = CalendarsViewState()
) {

    private val _dates: List<>

    override fun dispatch(action: CalendarsAction) = when (action) {

        else -> {}
    }

}