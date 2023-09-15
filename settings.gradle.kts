enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "GetBetter"
include(
    ":shared:core:vm",
    ":shared:core:util",
    ":shared:core:network",

    ":shared:features:auth:api",
    ":shared:features:auth:data",
    ":shared:features:auth:presentation",

    ":shared:features:home:presentation",

    ":shared:features:detail:presentation",

    ":androidApp:app",
    ":androidApp:activity",
    ":androidApp:core:compose",
    ":androidApp:features:auth",
    ":androidApp:features:home",
    ":androidApp:features:detail",

    ":shared:resources",
    ":shared:umbrella"
)