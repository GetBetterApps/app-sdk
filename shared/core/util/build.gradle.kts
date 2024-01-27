@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.STRING
import com.codingfeline.buildkonfig.compiler.FieldSpec.Type.INT


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
        buildConfigField(STRING, "name", "value")
    }

    targetConfigs {
        create("android") {
            buildConfigField(INT, "RUSTORE_AD_ID", 1494678.toString())
            buildConfigField(INT, "AD_ID", 1494645.toString())
        }

        create("ios") {
            buildConfigField(INT, "AD_ID", 5517759.toString())
        }
    }
}
