package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

sealed interface CalendarsAction : UIContract.Action {

    data object LoadMoreNextDates : CalendarsAction

    data class DateClick(val id: Long) : CalendarsAction
}