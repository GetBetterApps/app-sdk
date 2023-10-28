package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DatetimeFormatter {

    fun Long.convertToServerDatetime(): Long {
        // GMT+0
        val instant = Instant.fromEpochMilliseconds(this)
        val diffMillis = instant.offsetIn(TimeZone.currentSystemDefault()).totalSeconds * 1000

        val localInstant = Instant.fromEpochMilliseconds(this - diffMillis)

        val serverTimeZone = TimeZone.of("GMT+3")
        val serverDatetime = localInstant.toLocalDateTime(serverTimeZone)

        return serverDatetime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()
    }

}