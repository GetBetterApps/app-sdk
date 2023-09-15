@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.util.libs
import org.jetbrains.kotlin.gradle.plugin.mpp.BitcodeEmbeddingMode

plugins {
    `kmm-shared-module-plugin`
    kotlin("native.cocoapods")
    alias(libs.plugins.moko.resources)
}

android {
    namespace = com.velkonost.getbetter.ANDROID_PACKAGE
}

kotlin {
    cocoapods {
        summary = "GetBetter iOS SDK"
        homepage = "https://google.com"
        ios.deploymentTarget = "15.0"

        podfile = project.file("${project.rootDir}/iosApp/Podfile")

        framework {
            isStatic = true
            baseName = "SharedSDK"

            export(projects.shared.core.network)
            export(projects.shared.core.util)
            export(projects.shared.core.vm)
            export(projects.shared.resources)

            export(projects.shared.features.auth.api)
            export(projects.shared.features.auth.presentation)
            export(projects.shared.features.home.presentation)
            export(projects.shared.features.detail.presentation)

            export(libs.kermit.log)
            export(libs.moko.resources)
            export(libs.firebase.auth)

            transitiveExport = true
            embedBitcode(BitcodeEmbeddingMode.BITCODE)
        }
    }

    sourceSets {

        commonMain {
            dependencies {
                implementation(projects.shared.core.network)
                implementation(projects.shared.core.util)

                implementation(projects.shared.features.auth.data)
                implementation(projects.shared.features.auth.presentation)
                implementation(projects.shared.features.home.presentation)
                implementation(projects.shared.features.detail.presentation)

                implementation(libs.koin.core)
                implementation(libs.moko.resources)
                implementation(libs.firebase.auth)
            }
        }

        androidMain {

        }

        iosMain {
            dependencies {
                api(projects.shared.core.network)
                api(projects.shared.core.util)
                api(projects.shared.core.vm)
                api(projects.shared.resources)

                api(projects.shared.features.auth.api)
                api(projects.shared.features.auth.presentation)
                api(projects.shared.features.home.presentation)
                api(projects.shared.features.detail.presentation)

                api(libs.kermit.log)
                api(libs.moko.resources)
                api(libs.firebase.auth)
            }
        }

    }
}

multiplatformResources {
    multiplatformResourcesPackage = SHARED_PACKAGE
    multiplatformResourcesClassName = "MR"
    disableStaticFrameworkWarning = true
    multiplatformResourcesVisibility = dev.icerock.gradle.MRVisibility.Internal
}