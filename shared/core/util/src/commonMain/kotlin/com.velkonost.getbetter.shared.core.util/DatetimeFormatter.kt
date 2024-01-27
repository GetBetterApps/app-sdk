package com.velkonost.getbetter.shared.core.util

import com.velkonost.getbetter.BuildKonfig
import com.velkonost.getbetter.shared.resources.SharedR
import dev.icerock.moko.resources.desc.Resource
import dev.icerock.moko.resources.desc.ResourceFormatted
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.offsetIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.time.Duration

object DatetimeFormatter {

    private val SERVER_TIMEZONE = TimeZone.of("GMT+3")

    private const val SECOND_MILLIS = 1000
    private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
    private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
    const val DAY_MILLIS = 24 * HOUR_MILLIS

    private const val DAY_FORMAT = "dd"
    private const val DAY_MONTH_FORMAT = "dd.MM"
    private const val FULL_DATE_FORMAT = "dd.MM.yyyy"
    private const val DAY_OF_WEEK_FORMAT = "EEE"
    private const val HOURS_MINUTES_FORMAT = "HH:mm"
    private const val YEAR_FORMAT = "yyyy"
    private const val MONTH_DAY_FORMAT = "LLLL dd"

    val todayMillis: Long
        get() {
            val timeZone = TimeZone.currentSystemDefault()
            return Clock.System.todayIn(timeZone).atStartOfDayIn(timeZone).toEpochMilliseconds()
        }

    fun Long.convertToServerDatetime(): Long {
        // GMT+0
        val instant = Instant.fromEpochMilliseconds(this)
        instant.minus(Duration.ZERO)

        // Diff with local time in millis
        val diffMillis = instant.offsetIn(TimeZone.currentSystemDefault()).totalSeconds * 1000

        val localInstant = Instant.fromEpochMilliseconds(this - diffMillis)

        val serverDatetime = localInstant.toLocalDateTime(SERVER_TIMEZONE)

        return serverDatetime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()
    }

    fun Long.convertToLocalDatetime(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())
        val requestedMillis = requestedTime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()

        val nowDatetime = Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
        val nowMillis = nowDatetime.toInstant(UtcOffset.ZERO).toEpochMilliseconds()

        val diff = nowMillis - requestedMillis

        return when {
            diff < MINUTE_MILLIS -> {
                StringDesc.Resource(SharedR.strings.date_just_now)
            }

            diff < 2 * MINUTE_MILLIS -> {
                StringDesc.Resource(SharedR.strings.date_one_min)
            }

            diff < 59 * MINUTE_MILLIS -> {
                StringDesc.ResourceFormatted(
                    SharedR.strings.date_minutes,
                    "${diff / MINUTE_MILLIS}"
                )
            }

            diff < 2 * HOUR_MILLIS -> {
                StringDesc.Resource(SharedR.strings.date_one_hour)
            }

            diff < 24 * HOUR_MILLIS -> {
                StringDesc.ResourceFormatted(
                    SharedR.strings.date_hours,
                    "${diff / HOUR_MILLIS}"
                )
            }

            diff < 48 * HOUR_MILLIS -> {
                StringDesc.ResourceFormatted(
                    SharedR.strings.date_yesterday,
                    requestedTime.format(HOURS_MINUTES_FORMAT)
                )
            }

            requestedTime.year == nowDatetime.year -> {
                StringDesc.ResourceFormatted(
                    SharedR.strings.date_same_year,
                    requestedTime.format(DAY_MONTH_FORMAT)
                )
            }

            else -> {
                StringDesc.ResourceFormatted(
                    SharedR.strings.date_same_year,
                    requestedTime.format(FULL_DATE_FORMAT)
                )
            }
        }
    }

    fun Long.convertToLocalDatetimeWithoutRelation(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return StringDesc.ResourceFormatted(
            SharedR.strings.date_same_year,
            requestedTime.format(FULL_DATE_FORMAT)
        )
    }

    fun Long.convertToDay(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return StringDesc.ResourceFormatted(
            SharedR.strings.date_same_year,
            requestedTime.format(DAY_FORMAT)
        )
    }

    fun Long.convertToDayOfWeek(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return StringDesc.ResourceFormatted(
            SharedR.strings.date_same_year,
            requestedTime.format(DAY_OF_WEEK_FORMAT)
        )
    }

    fun Long.convertToYear(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return StringDesc.ResourceFormatted(
            SharedR.strings.date_same_year,
            requestedTime.format(YEAR_FORMAT)
        )
    }

    fun Long.convertToMonthDay(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return StringDesc.ResourceFormatted(
            SharedR.strings.date_same_year,
            requestedTime.format(MONTH_DAY_FORMAT)
        )
    }

    fun Long.convertToShortDateWithoutRelation(): StringDesc {
        val requestedTime = Instant.fromEpochMilliseconds(this)
            .toLocalDateTime(TimeZone.currentSystemDefault())

        return StringDesc.ResourceFormatted(
            SharedR.strings.date_same_year,
            requestedTime.format(DAY_MONTH_FORMAT)
        )
    }

}

expect fun LocalDateTime.format(format: String): String