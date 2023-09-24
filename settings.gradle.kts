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
    ":shared:core:datastore",

    ":shared:features:userinfo:api",
    ":shared:features:userinfo:data",

    ":shared:features:auth:api",
    ":shared:features:auth:data",
    ":shared:features:auth:domain",
    ":shared:features:auth:presentation",

    ":shared:features:profile:api",
    ":shared:features:profile:data",
    ":shared:features:profile:presentation",

    ":shared:features:home:presentation",

    ":shared:features:detail:presentation",

    ":shared:features:wisdom:presentation",

    ":shared:features:social:api",
    ":shared:features:social:data",
    ":shared:features:social:presentation",

    ":shared:features:calendars:api",
    ":shared:features:calendars:data",
    ":shared:features:calendars:presentation",

    ":shared:features:diary:api",
    ":shared:features:diary:data",
    ":shared:features:diary:presentation",

    ":androidApp:app",
    ":androidApp:activity",
    ":androidApp:core:compose",
    ":androidApp:features:auth",

    ":androidApp:features:home",
    ":androidApp:features:detail",

    ":androidApp:features:social",
    ":androidApp:features:diary",
    ":androidApp:features:calendars",
    ":androidApp:features:wisdom",
    ":androidApp:features:profile",

    ":shared:resources",
    ":shared:umbrella"
)