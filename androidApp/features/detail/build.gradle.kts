import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.features,
        projects.androidApp.features.detail
    )
}

dependencies {
    implementation(projects.shared.core.util)
    implementation(projects.shared.resources)
    implementation(projects.shared.features.detail.presentation)

    implementation(projects.androidApp.core.compose)
}
