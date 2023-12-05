import com.velkonost.getbetter.ANDROID_PACKAGE
import com.velkonost.getbetter.join

plugins {
    `android-ui-plugin`
}

android {
    namespace = ANDROID_PACKAGE.join(
        projects.androidApp.features,
        projects.androidApp.features.taskdetail
    )
}

dependencies {
    implementation(libs.androidx.compose.paging)

    implementation(projects.shared.core.util)
    implementation(projects.shared.features.createnote.presentation)
    api(projects.shared.features.taskdetail.presentation)

    implementation(projects.shared.resources)
    implementation(projects.androidApp.core.compose)
    
    implementation(projects.androidApp.features.areadetail)
}
