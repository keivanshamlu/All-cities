import configs.androidFeature
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        kotlin(ANDROID)
        kotlin(KAPT)
        id(NAVIGATION_SAFEARGS_KOTLIN)
        id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    }
    id("kotlin-android")
}
androidFeature()
dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.Utility.BASES))
    implementation(project(Modules.Utility.BASES_ANDROID))
    implementation(project(Modules.NAVIGATION))
    implementation("com.google.android.gms:play-services-maps:17.0.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.2")
}
