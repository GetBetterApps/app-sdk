import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.features,
        projects.androidApp.features.profile
    )
}

dependencies {
    implementation(projects.shared.core.util)
    implementation(projects.shared.core.model)
    implementation(projects.shared.resources)
    implementation(projects.shared.features.profile.presentation)

    implementation(projects.androidApp.core.utils)
    implementation(projects.androidApp.core.compose)

}
