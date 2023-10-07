import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.features,
        projects.androidApp.features.areadetail
    )
}

dependencies {
    implementation(libs.androidx.compose.paging)

    implementation(projects.shared.core.util)
    api(projects.shared.features.areadetail.presentation)

    implementation(projects.shared.resources)
    implementation(projects.androidApp.core.compose)
}
