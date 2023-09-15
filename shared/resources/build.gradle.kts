@file:Suppress("DSL_SCOPE_VIOLATION")

import com.velkonost.getbetter.SHARED_PACKAGE
import com.velkonost.getbetter.join
import com.velkonost.getbetter.util.libs

plugins {
    `kmm-shared-module-plugin`
    alias(libs.plugins.moko.resources)
}

android {
    namespace = SHARED_PACKAGE.join(
        projects.shared.resources
    )
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(libs.moko.resources)
            }
        }

        androidMain {
            dependencies {
                api(libs.moko.resources.compose)
            }
        }
    }
}

multiplatformResources {
    multiplatformResourcesPackage = SHARED_PACKAGE.join(projects.shared.resources)
    multiplatformResourcesClassName = "SharedR"
    disableStaticFrameworkWarning = true
}
