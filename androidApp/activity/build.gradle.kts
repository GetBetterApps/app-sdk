import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(projects.androidApp.activity)
}

dependencies {
    implementation(projects.androidApp.core.compose)

    implementation(projects.androidApp.features.auth)
    implementation(projects.androidApp.features.home)
    implementation(projects.androidApp.features.detail)
    implementation(projects.androidApp.features.profile)
    implementation(projects.androidApp.features.wisdom)

    implementation(projects.shared.core.util)
    implementation(projects.shared.core.vm)
    implementation(projects.shared.resources)
}
