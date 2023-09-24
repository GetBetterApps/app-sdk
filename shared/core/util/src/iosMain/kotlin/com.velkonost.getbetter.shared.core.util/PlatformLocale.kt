package com.velkonost.getbetter.shared.core.util

import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

actual val locale
    get() = NSLocale.currentLocale.languageCode
