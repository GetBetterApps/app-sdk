@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-app-plugin`
    alias(libs.plugins.android.crashlytics)
}

android {
    namespace = ANDROID_PACKAGE.join(projects.androidApp.app)
}

dependencies {
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)

    implementation(projects.shared.umbrella)
    implementation(projects.androidApp.activity)
}
