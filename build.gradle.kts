plugins {
    kotlin("multiplatform") version "1.7.22"
    kotlin("plugin.serialization") version "1.7.20"
}

group = "me.rahim"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}
val ktor_version = "2.2.1"
val okioVersion = "3.2.0"
var encoding = "1.2.1"
kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnit()
        }
    }
    js(LEGACY) {
        browser {
            commonWebpackConfig {
                cssSupport.enabled = true
            }
        }
        nodejs()
    }
    val hostOs = System.getProperty("os.name")
    val isMingwX64 = hostOs.startsWith("Windows")
    val nativeTarget = when {
        hostOs == "Mac OS X" -> macosX64("native")
        hostOs == "Linux" -> linuxX64("native")
        isMingwX64 -> mingwX64("native")
        else -> throw GradleException("Host OS is not supported in Kotlin/Native.")
    }

    
    sourceSets {
        val commonMain by getting {
            dependencies{
                implementation("io.ktor:ktor-client-core:$ktor_version")
                implementation("io.ktor:ktor-client-content-negotiation:$ktor_version")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
                implementation("com.michael-bull.kotlin-result:kotlin-result:1.1.13")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

                implementation("com.squareup.okio:okio:$okioVersion")
                implementation("com.ionspin.kotlin:multiplatform-crypto-libsodium-bindings:0.8.9")
                implementation("io.matthewnelson.kotlin-components:encoding-base32:$encoding")

            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val jvmMain by getting{
            dependencies{
                implementation("io.ktor:ktor-client-cio-jvm:$ktor_version")
            }
        }
        val jvmTest by getting
        val jsMain by getting
        val jsTest by getting
        val nativeMain by getting
        val nativeTest by getting
    }
}
