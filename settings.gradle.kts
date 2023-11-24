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
    ":shared:core:model",
    ":shared:core:network",
    ":shared:core:datastore",

    ":shared:features:splash:api",
    ":shared:features:splash:data",
    ":shared:features:splash:presentation",

    ":shared:features:userinfo:api",
    ":shared:features:userinfo:data",

    ":shared:features:areas:api",
    ":shared:features:areas:data",
    ":shared:features:notes:api",
    ":shared:features:notes:data",
    ":shared:features:tasks:api",
    ":shared:features:tasks:data",

    ":shared:features:likes:api",
    ":shared:features:likes:data",
    ":shared:features:comments:api",
    ":shared:features:comments:data",
    ":shared:features:feedback:api",
    ":shared:features:feedback:data",
    ":shared:features:follows:api",
    ":shared:features:follows:data",

    ":shared:features:auth:api",
    ":shared:features:auth:data",
    ":shared:features:auth:domain",
    ":shared:features:auth:presentation",

    ":shared:features:profile:api",
    ":shared:features:profile:data",
    ":shared:features:profile:presentation",

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
    ":shared:features:addarea:presentation",
    ":shared:features:areadetail:presentation",
    ":shared:features:notedetail:presentation",
    ":shared:features:profiledetail:presentation",

    ":androidApp:app",
    ":androidApp:activity",
    ":androidApp:core:compose",
    ":androidApp:core:utils",

    ":androidApp:features:auth",

    ":androidApp:features:splash",
    ":androidApp:features:social",
    ":androidApp:features:diary",
    ":androidApp:features:addarea",
    ":androidApp:features:areadetail",
    ":androidApp:features:notedetail",
    ":androidApp:features:profiledetail",
    ":androidApp:features:calendars",
    ":androidApp:features:wisdom",
    ":androidApp:features:profile",

    ":shared:resources",
    ":shared:umbrella"
)