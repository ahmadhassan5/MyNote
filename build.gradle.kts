plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(deps.plugins.android.application) apply false
    alias(deps.plugins.android.library) apply false
    kotlin("android").version("1.9.0").apply(false)
    kotlin("multiplatform").version("1.9.0").apply(false)
    alias(deps.plugins.jetBrains.compose) apply false
//    alias(deps.plugins.mokoResources) apply false
    alias(deps.plugins.sqlDelight) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}