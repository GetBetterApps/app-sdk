@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join
import com.velkonost.getbetter.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
    alias(libs.plugins.nativecoroutines)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.shared.core,
        projects.shared.core.vm
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation(libs.koin.core)
                implementation(libs.kotlinx.coroutines.core)

                api(libs.moko.resources)
                api(libs.rickclephas.kmm.viewmodel)

                implementation(projects.shared.core.util)
                implementation(projects.shared.resources)
            }
        }

        androidMain {
            dependencies {
                implementation(libs.androidx.lifecycle.viewmodel.compose)
            }
        }
    }
}
