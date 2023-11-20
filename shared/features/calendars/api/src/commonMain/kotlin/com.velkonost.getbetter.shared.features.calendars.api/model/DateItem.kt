package com.velkonost.getbetter.shared.features.calendars.api.model

import com.velkonost.getbetter.shared.core.util.DatetimeFormatter.DAY_MILLIS

data class DateItem(
    val millis: Long
)

fun DateItem.addDay() =
    DateItem(
        millis = millis + DAY_MILLIS
    )

fun DateItem.removeDay() =
    DateItem(
        millis = millis - DAY_MILLIS
    )