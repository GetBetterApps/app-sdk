package com.velkonost.getbetter.shared.core.util

import platform.Foundation.NSLocale
import platform.Foundation.NSUUID
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual fun randomUUID(): String = NSUUID().UUIDString()

actual val locale
    get() = NSLocale.currentLocale.languageCode
