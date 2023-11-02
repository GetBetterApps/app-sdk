@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.shared.core,
        projects.shared.core.util
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.squareup.okio)
                implementation(libs.kotlinx.datetime)
                implementation(libs.kotlinx.coroutines.core)

                api(libs.kermit.log)

                implementation(projects.shared.core.network)
            }
        }
    }
}
