@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
    alias(libs.plugins.nativecoroutines)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.shared.features,
        projects.shared.features.calendars,
        projects.shared.features.calendars.data
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)

                implementation(projects.shared.core.util)
                implementation(projects.shared.core.network)
                implementation(projects.shared.core.datastore)

                implementation(projects.shared.features.calendars.api)
            }
        }
    }
}