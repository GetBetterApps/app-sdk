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
        projects.shared.features.userinfo,
        projects.shared.features.userinfo.data
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.bundles.ktor.common)

                implementation(libs.firebase.auth)
                implementation(libs.firebase.firestore)

                implementation(projects.shared.core.util)
                implementation(projects.shared.core.network)
                implementation(projects.shared.features.userinfo.api)
            }
        }
    }
}