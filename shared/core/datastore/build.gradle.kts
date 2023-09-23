@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.shared.core,
        projects.shared.core.datastore
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.datastore)
            }
        }
    }
}
