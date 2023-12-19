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
        ios.deploymentTarget = "16.0"

        podfile = project.file("${project.rootDir}/iosApp/Podfile")

        framework {
            isStatic = true
            baseName = "SharedSDK"

            export(projects.shared.core.datastore)
            export(projects.shared.core.util)
            export(projects.shared.core.vm)
            export(projects.shared.core.network)
            export(projects.shared.resources)

            export(projects.shared.features.areas.api)
            export(projects.shared.features.notes.api)
            export(projects.shared.features.tasks.api)
            export(projects.shared.features.userinfo.api)

            export(projects.shared.features.auth.api)
            export(projects.shared.features.auth.domain)
            export(projects.shared.features.auth.presentation)

            export(projects.shared.features.splash.presentation)
            export(projects.shared.features.onboarding.presentation)

            export(projects.shared.features.social.presentation)
            export(projects.shared.features.diary.presentation)
            export(projects.shared.features.addarea.presentation)
            export(projects.shared.features.areadetail.presentation)
            export(projects.shared.features.profiledetail.presentation)
            export(projects.shared.features.notedetail.presentation)
            export(projects.shared.features.taskdetail.presentation)
            export(projects.shared.features.calendars.presentation)
            export(projects.shared.features.wisdom.presentation)
            export(projects.shared.features.profile.presentation)
            export(projects.shared.features.feedback.presentation)
            export(projects.shared.features.settings.presentation)
            export(projects.shared.features.abilities.presentation)
            export(projects.shared.features.abilitydetails.presentation)

            export(libs.kermit.log)
            export(libs.moko.resources)

            transitiveExport = true
            embedBitcode(BitcodeEmbeddingMode.BITCODE)
        }
    }

    sourceSets {

        commonMain {
            dependencies {
                implementation(projects.shared.core.datastore)
                implementation(projects.shared.core.network)
                implementation(projects.shared.core.util)

                implementation(projects.shared.features.areas.data)
                implementation(projects.shared.features.notes.data)
                implementation(projects.shared.features.tasks.data)
                implementation(projects.shared.features.userinfo.data)
                implementation(projects.shared.features.likes.data)
                implementation(projects.shared.features.comments.data)
                implementation(projects.shared.features.follows.data)
                implementation(projects.shared.features.calendars.data)
                implementation(projects.shared.features.feedback.data)
                implementation(projects.shared.features.abilities.data)
                implementation(projects.shared.features.affirmations.data)

                implementation(projects.shared.features.auth.data)
                implementation(projects.shared.features.auth.domain)
                implementation(projects.shared.features.auth.presentation)

                implementation(projects.shared.features.splash.data)
                implementation(projects.shared.features.splash.presentation)

                implementation(projects.shared.features.onboarding.data)
                implementation(projects.shared.features.onboarding.presentation)

                implementation(projects.shared.features.social.data)
                implementation(projects.shared.features.social.presentation)

                implementation(projects.shared.features.diary.data)
                implementation(projects.shared.features.diary.presentation)

                implementation(projects.shared.features.addarea.presentation)
                implementation(projects.shared.features.areadetail.presentation)
                implementation(projects.shared.features.profiledetail.presentation)
                implementation(projects.shared.features.notedetail.presentation)
                implementation(projects.shared.features.taskdetail.presentation)
                implementation(projects.shared.features.calendars.presentation)
                implementation(projects.shared.features.wisdom.presentation)
                implementation(projects.shared.features.feedback.presentation)
                implementation(projects.shared.features.settings.presentation)
                implementation(projects.shared.features.abilities.presentation)
                implementation(projects.shared.features.abilitydetails.presentation)

                implementation(projects.shared.features.profile.data)
                implementation(projects.shared.features.profile.presentation)

                implementation(libs.koin.core)
                implementation(libs.moko.resources)
            }
        }

        androidMain {
            dependencies {

            }
        }

        iosMain {
            dependencies {
                api(projects.shared.core.datastore)
                api(projects.shared.core.util)
                api(projects.shared.core.vm)
                api(projects.shared.core.network)
                api(projects.shared.resources)

                api(projects.shared.features.areas.api)
                api(projects.shared.features.notes.api)
                api(projects.shared.features.tasks.api)
                api(projects.shared.features.userinfo.api)

                api(projects.shared.features.auth.api)
                api(projects.shared.features.auth.domain)
                api(projects.shared.features.auth.presentation)

                api(projects.shared.features.splash.presentation)
                api(projects.shared.features.onboarding.presentation)
                api(projects.shared.features.social.presentation)
                api(projects.shared.features.diary.presentation)
                api(projects.shared.features.addarea.presentation)
                api(projects.shared.features.areadetail.presentation)
                api(projects.shared.features.profiledetail.presentation)
                api(projects.shared.features.notedetail.presentation)
                api(projects.shared.features.taskdetail.presentation)
                api(projects.shared.features.calendars.presentation)
                api(projects.shared.features.wisdom.presentation)
                api(projects.shared.features.profile.presentation)
                api(projects.shared.features.feedback.presentation)
                api(projects.shared.features.settings.presentation)
                api(projects.shared.features.abilities.presentation)
                api(projects.shared.features.abilitydetails.presentation)

                api(libs.kermit.log)
                api(libs.moko.resources)
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