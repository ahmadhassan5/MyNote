plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    alias(deps.plugins.jetBrains.compose)
    alias(deps.plugins.android.library)
    alias(deps.plugins.sqlDelight)
    id("kotlin-parcelize")
}

kotlin {
    androidTarget()
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
                api(deps.preCompose)
                api(deps.preComposeViewModel)
//                api(deps.decompose)
//                implementation(deps.decompose.extensions)
//                api(deps.koinCompose)
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
                implementation(deps.sqlDelight.sqlite)
                api(deps.coroutinesSwing)
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
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}