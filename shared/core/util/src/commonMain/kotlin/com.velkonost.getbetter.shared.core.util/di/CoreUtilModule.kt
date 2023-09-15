package com.velkonost.getbetter.shared.core.util.di

import com.velkonost.getbetter.shared.core.util.BaseLogger
import org.koin.dsl.module

val CoreUtilModule = module {
    factory { (tag: String?) -> if (tag != null) BaseLogger.withTag(tag) else BaseLogger }
}