import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.features,
        projects.androidApp.features.diary
    )
}

dependencies {
    implementation(projects.shared.core.util)
    implementation(projects.shared.resources)

    implementation(projects.shared.features.areas.api)
    implementation(projects.shared.features.diary.presentation)

    implementation(projects.androidApp.core.compose)
    implementation(projects.androidApp.features.areadetail)
}
