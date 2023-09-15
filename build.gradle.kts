buildscript {
    repositories.applyDefault()

    plugins.apply("com.velkonost.getbetter.checks.detekt")
    plugins.apply("com.velkonost.getbetter.checks.ktlint")
    plugins.apply("com.velkonost.getbetter.checks.spotless")
    plugins.apply("com.velkonost.getbetter.checks.dependency-updates")
}
