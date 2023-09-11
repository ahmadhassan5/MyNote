import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    alias(deps.plugins.jetBrains.compose)
}

//group = ""
//version = "1.0-SNAPSHOT"

kotlin {
    jvm {
        /*compilations.all {
            kotlinOptions.jvmTarget = "16"
        }*/
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":shared"))
                implementation(compose.desktop.currentOs)
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = ""
            packageVersion = "1.0.0"
        }
    }
}
