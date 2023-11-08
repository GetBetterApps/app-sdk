package com.velkonost.getbetter.shared.core.util

import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale


actual fun LocalDateTime.format(
    format: String
): String =
    DateTimeFormatter.ofPattern(format, Locale.getDefault()).format(this.toJavaLocalDateTime())