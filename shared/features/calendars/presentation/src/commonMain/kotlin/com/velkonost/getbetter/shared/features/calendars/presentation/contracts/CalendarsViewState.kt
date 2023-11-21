package com.velkonost.getbetter.shared.features.calendars.presentation.contracts

import com.velkonost.getbetter.shared.core.model.EntityType
import com.velkonost.getbetter.shared.core.vm.contracts.UIContract
import dev.icerock.moko.resources.desc.StringDesc

data class CalendarsViewState(
    val isLoading: Boolean = false,
    val datesState: DatesState = DatesState()
) : UIContract.State

data class DatesState(
    val isPreviousLoading: Boolean = true,
    val isNextLoading: Boolean = true,
    val selectedDate: SelectedDate? = null,
    val items: List<DateUIItem> = emptyList()
)

data class SelectedDate(
    val id: Long,
    val year: StringDesc,
    val monthDay: StringDesc
)

data class DateUIItem(
    val id: Long,
    val day: StringDesc,
    val dayOfWeek: StringDesc,
    val items: List<ActionUIItem<*>> = emptyList()
)

data class ActionUIItem<T>(
    val type: EntityType,
    val isLoading: Boolean = true,
    val data: T
)