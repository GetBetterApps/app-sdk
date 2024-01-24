@file:Suppress("UnstableApiUsage", "UNUSED_VARIABLE", "DSL_SCOPE_VIOLATION")

import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.extensions.BuildTypeDebug
import com.velkonost.getbetter.extensions.BuildTypeRelease
import com.velkonost.getbetter.extensions.getKeystoreProperty
import com.velkonost.getbetter.util.libs

plugins {
    id("com.android.application")
    kotlin("android")
    id("com.google.gms.google-services")

    id("com.velkonost.getbetter.checks.detekt")
    id("com.velkonost.getbetter.checks.ktlint")
    id("com.velkonost.getbetter.checks.spotless")
    id("com.velkonost.getbetter.checks.dependency-updates")
}

repositories {
    applyDefault()
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        applicationId = ANDROID_PACKAGE
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        multiDexEnabled = true
        setProperty("archivesBaseName", "v$versionCode($versionName)")
    }

    applicationVariants.all {
        val variant = this
        variant.outputs
            .map { it as BaseVariantOutputImpl }
            .forEach { output ->
                output.outputFileName =
                    "app-${variant.baseName}-${variant.buildType.name}-${variant.versionName}.apk"
            }
    }

    signingConfigs {
        val release by creating {
            storeFile = file(getKeystoreProperty("STORE_FILE"))
            storePassword = getKeystoreProperty("STORE_PASSWORD")
            keyAlias = getKeystoreProperty("KEY_ALIAS")
            keyPassword = getKeystoreProperty("KEY_PASSWORD")
        }
    }

    buildTypes {
        val debug by getting {
//            applicationIdSuffix = BuildTypeDebug.applicationIdSuffix
            versionNameSuffix = BuildTypeDebug.versionNameSuffix
            isMinifyEnabled = BuildTypeDebug.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeDebug.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeDebug.enableAndroidTestCoverage
        }

        val release by getting {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = BuildTypeRelease.isMinifyEnabled
            enableUnitTestCoverage = BuildTypeRelease.enableUnitTestCoverage
            enableAndroidTestCoverage = BuildTypeRelease.enableAndroidTestCoverage
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    kotlinOptions {
        jvmTarget = "18"
    }

    sourceSets.all {
        java.srcDirs("src/$name/kotlin")
    }

    dependencies {
        implementation(libs.koin.core)
        implementation(libs.koin.androidx.compose)

        implementation(platform(libs.firebase.android.core))
        implementation(libs.firebase.android.crashlytics)
        implementation(libs.firebase.android.messaging)

        implementation(libs.yandex.ad)
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
