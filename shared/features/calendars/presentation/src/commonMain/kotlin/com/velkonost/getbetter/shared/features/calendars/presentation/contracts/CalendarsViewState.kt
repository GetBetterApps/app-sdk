package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import dev.icerock.moko.resources.desc.StringDesc

data class CalendarsViewState(
    val isLoading: Boolean = false,
    val datesState: DatesState = DatesState()
) : UIContract.State

data class DatesState(
    val isPreviousLoading: Boolean = false,
    val isNextLoading: Boolean = true,
    val items: List<DateUIItem> = emptyList()
)

data class DateUIItem(
    val date: StringDesc,
    val items: List<String> = emptyList()
)