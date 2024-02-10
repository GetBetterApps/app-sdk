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

    ":shared:features:onboarding:api",
    ":shared:features:onboarding:data",
    ":shared:features:onboarding:presentation",

    ":shared:features:userinfo:api",
    ":shared:features:userinfo:data",

    ":shared:features:areas:api",
    ":shared:features:areas:data",

    ":shared:features:notes:api",
    ":shared:features:notes:data",

    ":shared:features:affirmations:api",
    ":shared:features:affirmations:data",

    ":shared:features:abilities:api",
    ":shared:features:abilities:data",
    ":shared:features:abilities:presentation",

    ":shared:features:abilitydetails:presentation",

    ":shared:features:tasks:api",
    ":shared:features:tasks:data",

    ":shared:features:likes:api",
    ":shared:features:likes:data",

    ":shared:features:comments:api",
    ":shared:features:comments:data",

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

    ":shared:features:createnote:api",
    ":shared:features:createnote:data",
    ":shared:features:createnote:presentation",

    ":shared:features:addarea:api",
    ":shared:features:addarea:data",
    ":shared:features:addarea:presentation",

    ":shared:features:areadetail:api",
    ":shared:features:areadetail:data",
    ":shared:features:areadetail:presentation",

    ":shared:features:notedetail:api",
    ":shared:features:notedetail:data",
    ":shared:features:notedetail:presentation",

    ":shared:features:taskdetail:api",
    ":shared:features:taskdetail:data",
    ":shared:features:taskdetail:presentation",

    ":shared:features:profiledetail:presentation",

    ":shared:features:settings:presentation",

    ":shared:features:feedback:api",
    ":shared:features:feedback:data",
    ":shared:features:feedback:presentation",

    ":shared:features:subscription:api",
    ":shared:features:subscription:data",
    ":shared:features:subscription:presentation",

    ":androidApp:app",
    ":androidApp:activity",
    ":androidApp:core:compose",
    ":androidApp:core:utils",

    ":androidApp:features:auth",
    ":androidApp:features:splash",
    ":androidApp:features:onboarding",
    ":androidApp:features:social",
    ":androidApp:features:diary",
    ":androidApp:features:addarea",
    ":androidApp:features:areadetail",
    ":androidApp:features:notedetail",
    ":androidApp:features:profiledetail",
    ":androidApp:features:taskdetail",
    ":androidApp:features:calendars",
    ":androidApp:features:wisdom",
    ":androidApp:features:profile",
    ":androidApp:features:feedback",
    ":androidApp:features:settings",
    ":androidApp:features:abilities",
    ":androidApp:features:abilitydetails",

    ":shared:resources",
    ":shared:umbrella"
)