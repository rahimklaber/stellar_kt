import org.jetbrains.kotlin.gradle.utils.loadPropertyFromResources
import java.net.URI
import java.util.*

plugins {
    kotlin("multiplatform") version "2.1.0"
    kotlin("plugin.serialization") version "2.1.0"
    `maven-publish`
    signing
}

group = "me.rahimklaber"
version = "0.0.4"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
    maven("https://repo.kotlin.link")
}
val ktor_version = "3.0.3"
val okioVersion = "3.6.0"
var encoding = "1.2.1"
val coroutinesVersion = "1.10.1"
val serializationVersion = "1.8.0"
val datetimeVersion = "0.6.1"
val cryptoVersion = "0.9.2"

val localProperties = Properties()

try {
    localProperties.load(File("local.properties").inputStream())
}catch (e: Throwable){}

tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class).all {
    kotlinOptions.freeCompilerArgs = listOf("-Xcontext-receivers")
}
//



afterEvaluate {

    extensions.findByType<PublishingExtension>()?.apply {
        repositories {
            mavenLocal()
//            maven{
//                url = URI.create("https://central.sonatype.org/")
//                credentials {
//                }
//            }
        }


        publications.withType<MavenPublication>().configureEach {
            val publication = this
            val javadocJar = tasks.register("${publication.name}JavadocJar", Jar::class) {
                archiveClassifier.set("javadoc")
                archiveAppendix.set(name)
            }
            artifact(javadocJar)
            pom {
                name = "Kotlin Multiplatform Stellar SDK"
                description = "A multiplatform SDK for the Stellar Blockchain"
                url = "https://github.com/rahimklaber/stellar_kt"
                developers {
                    developer {
                        id = "rahim"
                        name = "Rahim Klabér"
                        email = "rahimklaber2@gmail.com"
                    }
                }

                licenses {
                    license {
                        name = "The Apache License, Version 2.0"
                        url = "https://www.apache.org/licenses/LICENSE-2.0.txt"
                    }
                }


                scm {
                    url = "https://github.com/rahimklaber/stellar_kt"
                }
            }
        }
//        extensions.findByType<SigningExtension>()!!.apply sc@{
//            val publishing = extensions.findByType<PublishingExtension>() ?: return@sc
//            val key = localProperties["signingKey"]?.toString()?.replace("\\n", "\n")
//            val password = localProperties["signingPassword"]?.toString()
//
//            useInMemoryPgpKeys(key, password)
//            sign(publishing.publications)
//        }
    }
}


kotlin {
    jvm {
        compilations.all {
//            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) {
        binaries.library()
        browser {

//            commonWebpackConfig {
//                cssSupport.enabled = true
//            }
        }
        nodejs()
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
//    val nativeTarget = when {
//        hostOs == "Mac OS X" -> macosX64("native")
//        hostOs == "Linux" -> linuxX64("native")
//        isMingwX64 -> mingwX64("native")
//        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
//    }


    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.6.0")

                implementation("com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings:$cryptoVersion")
                implementation("io.matthewnelson.kotlin-components:encoding-base16:$encoding")
                implementation("io.matthewnelson.kotlin-components:encoding-base32:$encoding")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1")
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
                implementation("network.lightsail:stellar-sdk:0.44.0")

//                implementation("com.squareup:kotlinpoet:1.14.2")
//                api("space.kscience:kmath-core:0.3.1")
            }
        }
        val jvmTest by getting{
            dependencies{
            }
        }
        val jsMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-js:$ktor_version")
            }
        }
        val jsTest by getting
//        val nativeMain by getting{
//            dependencies {
//                implementation("io.ktor:ktor-client-winhttp:$ktor_version")
//        }
//        }
//        val nativeTest by getting
    }
}
