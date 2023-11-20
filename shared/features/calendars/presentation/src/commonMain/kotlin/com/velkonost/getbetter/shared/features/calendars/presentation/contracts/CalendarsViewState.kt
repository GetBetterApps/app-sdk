package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract

data class CalendarsViewState(
    val isLoading: Boolean = false,
    val dates: List<DateUIItem> = emptyList()
) : UIContract.State

data class DateUIItem(
    val date: String = "",
    val items: List<String> = emptyList()
)