package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DatetimeFormatter {

    private val SERVER_TIMEZONE = TimeZone.of("GMT+3")

    fun Long.convertToServerDatetime(): Long {
        // GMT+0
        val instant = Instant.fromEpochMilliseconds(this)

        // Diff with local time in millis
        val diffMillis = instant.offsetIn(TimeZone.currentSystemDefault()).totalSeconds * 1000

        val localInstant = Instant.fromEpochMilliseconds(this - diffMillis)

        val serverDatetime = localInstant.toLocalDateTime(SERVER_TIMEZONE)

        return serverDatetime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()
    }

    fun Long.convertToLocalDatetime(): String {
        return Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
            .format("dd.MM.yyyy")
    }

}

expect fun LocalDateTime.format(format: String): String