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
        projects.shared.features.profile,
        projects.shared.features.profile.presentation
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(projects.shared.core.vm)

                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)

                implementation(projects.shared.core.util)
                implementation(projects.shared.core.model)
                implementation(projects.shared.core.network)

                implementation(projects.shared.features.auth.api)
                implementation(projects.shared.features.profile.api)
                implementation(projects.shared.features.userinfo.api)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.koin.androidx.compose)
            }
        }
    }
}