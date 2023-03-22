plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight")
    id("kotlin-parcelize")
}

kotlin {
    android()
    jvm("desktop")
    ios()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
            linkerOpts("-lsqlite3")
        }
    }

    sourceSets {

        val commonMain by getting {
            dependencies {
                api(compose.runtime)
                api(compose.ui)
                api(compose.foundation)
                api(compose.material)
                implementation(deps.koin.core)
                implementation(deps.kotlinxDateTime)
                implementation(deps.sqlDelight.runtime)
                api(deps.decompose)
                implementation(deps.decompose.extensions)
                implementation(deps.coroutinesCore)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies {
                api(deps.koin.android)
                implementation(deps.sqlDelight.android)
            }
        }
//        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by getting {
            dependencies {
                implementation(deps.sqlDelight.native)
            }
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by getting {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
            }
        }
    }
}

sqldelight {
    databases {
        create("NoteDatabase") {
            packageName.set("com.ahmadhassan.mynote")
        }
    }
}

android {
    namespace = "com.ahmadhassan.mynote"
    compileSdk = deps.versions.compileSdk.get().toInt()
    defaultConfig {
        minSdk = deps.versions.minSdk.get().toInt()
        targetSdk = deps.versions.targetSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}