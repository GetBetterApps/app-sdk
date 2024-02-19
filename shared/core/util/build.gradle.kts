@file:Suppress("DSL_SCOPE_VIOLATION")

import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join


plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.ksp)
    alias(libs.plugins.codingfeline.buildkonfig)
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
                implementation(projects.shared.resources)
            }
        }
    }
}

buildkonfig {
    packageName = "com.velkonost.getbetter"
    exposeObjectWithName = "UtilBuildKonfig"

    defaultConfigs {
        buildConfigField(STRING, "AD_ID", "")
        buildConfigField(STRING, "YOOKASSA_KEY", "live_MzMxMjA5lf8FLM_nMtwUESA8dc8wrvO3X4rtmPgTdng")
    }

    targetConfigs {
        create("android") {
            buildConfigField(STRING, "AD_ID", "R-M-5855367-1")
        }

        create("ios") {
            buildConfigField(STRING, "AD_ID", "R-M-5517759-1")
        }
    }
}
