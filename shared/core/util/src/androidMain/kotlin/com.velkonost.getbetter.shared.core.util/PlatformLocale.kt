package com.velkonost.getbetter.shared.core.util

import java.util.Locale

actual val locale
    get() = Locale.getDefault().language
