// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.diffplug.gradle.spotless.SpotlessExtension

buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        gradlePluginPortal()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots")

        maven(url = "https://jitpack.io")
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.21")
        val libs = project.extensions.getByType<VersionCatalogsExtension>().named("libs") as org.gradle.accessors.dm.LibrariesForLibs
        classpath(libs.gradlePlugin)
        classpath(libs.kotlinPlugin)
        classpath(libs.hiltPlugin)
        classpath(libs.spotlessPlugin)
        classpath(libs.navigationPlugin)
        classpath(libs.dokkaPlugin)
        classpath(libs.dependencyUpdatePlugin)
        classpath(libs.jacocoPlugin)
        classpath(libs.googleServicePlugin)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply(plugin ="com.github.ben-manes.versions")
apply(plugin ="com.diffplug.spotless")

fun String.isNonStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(this)
    return isStable.not()
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        candidate.version.isNonStable()
    }
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/DependencyUpdates"
    reportfileName = "dependency_update_report"
}

configure<SpotlessExtension> {
    kotlin {
        target(".kt")
        ktlint(libs.versions.ktlintPlugin.get())
            .userData(mapOf("indent_size" to "4", "continuation_indent_size" to "4"))
        trimTrailingWhitespace()
        endWithNewline()
        prettier()
    }
    kotlinGradle {
        target(".kts")
        trimTrailingWhitespace()
        endWithNewline()
        prettier()
    }
    groovyGradle {
        target(".gradle")
        greclipse()
        trimTrailingWhitespace()
        endWithNewline()
        prettier()
    }
    freshmark {
        target(".md")
        trimTrailingWhitespace()
        endWithNewline()
        prettier()
    }
}