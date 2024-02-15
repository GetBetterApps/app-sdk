@file:Suppress("UnstableApiUsage")

import com.velkonost.getbetter.util.libs
import gradle.kotlin.dsl.accessors._96aaa92c1a3d6567ab9284def8650ed9.implementation

plugins {
    id("com.android.library")
    kotlin("android")

    id("com.velkonost.getbetter.checks.detekt")
    id("com.velkonost.getbetter.checks.ktlint")
    id("com.velkonost.getbetter.checks.spotless")
}

repositories {
    applyDefault()
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.androidx.compose.compiler.get()
    }

    sourceSets.all {
        java.srcDirs("src/$name/kotlin")
    }
}

kotlin {
    jvmToolchain(18)

    sourceSets.all {
        languageSettings.apply {
            optIn("kotlin.RequiresOptIn")
            optIn("androidx.compose.animation.ExperimentalAnimationApi")
            optIn("androidx.compose.material3.ExperimentalMaterial3Api")
            optIn("androidx.compose.ui.unit.ExperimentalUnitApi")
        }
    }
}

dependencies {
    api(libs.bundles.app.ui)

    implementation(libs.koin.core)
    implementation(libs.koin.androidx.compose)

    implementation(libs.yandex.ad)
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
