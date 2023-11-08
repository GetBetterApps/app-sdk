package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.offsetIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime

object DatetimeFormatter {


    private val SERVER_TIMEZONE = TimeZone.of("GMT+3")

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    private const val DAY_MILLIS = 24 * HOUR_MILLIS

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
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val requestedMillis = requestedTime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()

        val nowDatetime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val nowMillis = nowDatetime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()

        val diff = nowMillis - requestedMillis

        if (diff < MINUTE_MILLIS) {
            return "Just now"
        } else if (diff < 2 * MINUTE_MILLIS) {
            return "1 min"
        } else if (diff < 59 * MINUTE_MILLIS) {
            return "${diff / MINUTE_MILLIS} mins"
        } else if (diff < 2 * HOUR_MILLIS) {
            return "1 hr"
        } else if (diff < 24 * HOUR_MILLIS) {
            return "${diff / HOUR_MILLIS} hrs"
        } else if (diff < 48 * HOUR_MILLIS) {
            return ("Yesterday at " + requestedTime.format("HH:mm"))
        } else if (requestedTime.year == nowDatetime.year) {
            return requestedTime.format("dd.MM")
        } else {
            return requestedTime.format("dd.MM.yyyy")
        }

//        return Instant.fromEpochMilliseconds(this)
//            .toLocalDateTime(TimeZone.currentSystemDefault())
//            .format("dd.MM.yyyy HH:mm")
    }

    fun Long.isPast(): Boolean {
        val firstDatetime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        val nowDatetime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())

        return nowDatetime.compareTo(firstDatetime) > 1
    }

}

expect fun LocalDateTime.format(format: String): String