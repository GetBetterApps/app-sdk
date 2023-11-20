package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class CalendarsViewState(
    val isLoading: Boolean = false,
    val datesState: DatesState = DatesState()
) : UIContract.State

data class DatesState(
    val isPreviousLoading: Boolean = false,
    val isNextLoading: Boolean = true,
    val dates: List<DateUIItem> = emptyList()
)

data class DateUIItem(
    val date: String = "",
    val items: List<String> = emptyList()
)