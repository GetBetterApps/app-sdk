
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.maven
import org.jetbrains.kotlin.gradle.plugin.KotlinDependencyHandler
import com.velkonost.getbetter.util.libs

fun RepositoryHandler.applyDefault() {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    maven("https://plugins.gradle.org/m2/")
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/snapshots/")

}