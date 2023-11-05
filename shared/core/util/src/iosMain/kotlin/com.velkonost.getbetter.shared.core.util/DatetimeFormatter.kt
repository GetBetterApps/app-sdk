package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

actual fun LocalDateTime.format(format: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format
//this.date.toNSDateComponents().date()

    return dateFormatter.stringFromDate(
        this.toInstant(TimeZone.currentSystemDefault()).toNSDate()
    )
}