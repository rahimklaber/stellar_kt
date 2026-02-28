import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    kotlin("multiplatform") version "2.3.10"
    kotlin("plugin.serialization") version "2.3.10"
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
val ktor_version = "3.4.0"
var encoding = "2.6.0"
val coroutinesVersion = "1.10.2"
val serializationVersion = "1.10.0"
val datetimeVersion = "0.6.1"
val cryptoVersion = "0.9.5"
val kotlinxIoVersion = "0.9.0"


kotlin {
    jvm {
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(IR) {
        binaries.library()
        nodejs()
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion")

                implementation("org.jetbrains.kotlinx:kotlinx-io-core:$kotlinxIoVersion")

                implementation("com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings:$cryptoVersion")
                implementation("io.matthewnelson.encoding:base32:${encoding}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting {
            dependencies {
                implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
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
    }
}
