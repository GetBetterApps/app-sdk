@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
    alias(libs.plugins.nativecoroutines)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.shared.features,
        projects.shared.features.calendars,
        projects.shared.features.calendars.presentation
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.shared.core.vm)
                api(projects.shared.core.util)

                implementation(projects.shared.core.model)
                implementation(projects.shared.resources)

                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)

                implementation(projects.shared.features.notes.api)
                implementation(projects.shared.features.areas.api)
                implementation(projects.shared.features.userinfo.api)
                implementation(projects.shared.features.comments.api)
                implementation(projects.shared.features.calendars.api)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.androidx.compose)
            }
        }
    }
}