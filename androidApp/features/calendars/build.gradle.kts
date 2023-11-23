import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join
import com.velkonost.getbetter.util.libs

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.features,
        projects.androidApp.features.calendars
    )
}

dependencies {
    implementation(libs.android.compose.lootie)

    implementation(projects.shared.core.util)
    implementation(projects.shared.core.model)
    implementation(projects.shared.resources)
    implementation(projects.shared.features.calendars.presentation)

    implementation(projects.androidApp.core.compose)
    implementation(projects.androidApp.features.notedetail)
    implementation(projects.androidApp.features.areadetail)
    implementation(projects.androidApp.features.profiledetail)
}
