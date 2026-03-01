import dev.adamko.dokkatoo.dokka.parameters.KotlinPlatform
import dev.adamko.dokkatoo.dokka.parameters.VisibilityModifier
import java.net.URI
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    `maven-publish`
    alias(libs.plugins.dokkatoo)
}

val ver = "14"
version = ver
group = "com.github.aniyomiorg"

val javaVersion = JavaVersion.VERSION_17

android {
    compileSdk = 34
    namespace = "eu.kanade.tachiyomi.animeextensions"

    defaultConfig {
        minSdk = 21
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    publishing {
        singleVariant("release")
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.fromTarget(javaVersion.toString()))
    }
}

dependencies {
    compileOnly(libs.okhttp)
    compileOnly(libs.jsoup)
    compileOnly(libs.rxjava)
    compileOnly(libs.rxandroid)
    compileOnly(libs.unifile)
    compileOnly(libs.injekt.core)
}

dokkatoo {
    moduleName.set("extensions-lib")
    moduleVersion.set(ver)
    dokkatooPublicationDirectory.set(layout.buildDirectory.dir("docs"))

    dokkatooSourceSets.configureEach {
        includes.from("Module.md")

        // Temporary workaround for https://github.com/Kotlin/dokka/issues/2876.
        analysisPlatform.set(KotlinPlatform.JVM)

        perPackageOption {
            matchingRegex.set("android.content")
            suppress.set(true)
        }

        documentedVisibilities(VisibilityModifier.PUBLIC, VisibilityModifier.PROTECTED)

        externalDocumentationLinks {
            create("okhttp5") {
                url.set(URI("https://square.github.io/okhttp/5.x/"))
            }

            create("jsoup") {
                url.set(URI("https://jsoup.org/apidocs/"))
                packageListUrl.set(URI("https://jsoup.org/apidocs/element-list"))
            }

            create("rxjava") {
                url.set(URI("https://reactivex.io/RxJava/1.x/javadoc/"))
            }
        }

        val packageRoot = projectDir.resolve("src/main/java/eu/kanade/tachiyomi/")
        sourceLink {
            localDirectory.set(packageRoot.resolve("animesource/"))
            remoteUrl.set(URI("https://github.com/komikku-app/anikku/tree/master/source-api/src/commonMain/kotlin/eu/kanade/tachiyomi/animesource/"))
            // The line number is wrong, so we're not going to highlight it.
            remoteLineSuffix.set("#")
        }

        sourceLink {
            localDirectory.set(packageRoot.resolve("network/"))
            remoteUrl.set(URI("https://github.com/komikku-app/anikku/tree/master/core/src/main/java/eu/kanade/tachiyomi/network/"))
            remoteLineSuffix.set("#") // Same as before.
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "extensions-lib"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
