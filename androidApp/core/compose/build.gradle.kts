@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join
import com.velkonost.getbetter.util.libs

plugins {
    `android-ui-plugin`
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.core,
        projects.androidApp.core.compose
    )
}

dependencies {
    implementation(projects.shared.core.vm)
    implementation(projects.shared.resources)
}
