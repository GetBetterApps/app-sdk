package com.velkonost.getbetter.shared.core.util

import java.util.Locale

actual fun randomUUID() = java.util.UUID.randomUUID().toString()

actual val locale: String
    get() = Locale.getDefault().language
