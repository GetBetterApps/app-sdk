@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join
import com.velkonost.getbetter.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.core,
        projects.androidApp.core.compose
    )
}

dependencies {
    api(libs.androidx.compose.icons)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.material3)

    implementation(libs.android.compose.lootie)
    implementation(libs.androidx.compose.paging)
    implementation(libs.android.richtext)

    implementation(projects.shared.core.vm)
    implementation(projects.shared.core.model)
    implementation(projects.shared.resources)
}
