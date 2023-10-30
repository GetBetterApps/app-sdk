package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter

actual fun LocalDateTime.format(format: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format

    return dateFormatter.stringFromDate(
        this.toInstant(UtcOffset.ZERO).toNSDate()
    )
}