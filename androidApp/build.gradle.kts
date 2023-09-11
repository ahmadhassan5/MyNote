plugins {
    alias(deps.plugins.android.application)
    kotlin("android")
}

android {
    namespace = "com.ahmadhassan.mynote"
    compileSdk = deps.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "com.ahmadhassan.mynote"
        minSdk = deps.versions.minSdk.get().toInt()
        targetSdk = deps.versions.targetSdk.get().toInt()
        versionCode = deps.versions.versionCode.get().toInt()
        versionName = deps.versions.versionName.get()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    /*implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.2.1")
    implementation("androidx.compose.ui:ui-tooling:1.2.1")
    implementation("androidx.compose.ui:ui-tooling-preview:1.2.1")
    implementation("androidx.compose.foundation:foundation:1.2.1")
    implementation("androidx.compose.material:material:1.2.1")
    implementation("androidx.activity:activity-compose:1.5.1")*/
//    implementation("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-4")

    implementation(project(":shared"))
    implementation(deps.bundles.compose.tooling)
    implementation(deps.activity.compose)
}