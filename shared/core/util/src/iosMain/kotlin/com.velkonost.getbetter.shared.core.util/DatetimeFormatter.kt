package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toNSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.autoupdatingCurrentLocale

actual fun LocalDateTime.format(format: String): String {
    val dateFormatter = NSDateFormatter()
    dateFormatter.dateFormat = format
    dateFormatter.locale = NSLocale.autoupdatingCurrentLocale

    return dateFormatter.stringFromDate(
        this.toInstant(TimeZone.currentSystemDefault()).toNSDate()
    )
}