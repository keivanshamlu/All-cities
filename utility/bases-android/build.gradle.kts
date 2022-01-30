import configs.androidFeature
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        kotlin(ANDROID)
        kotlin(KAPT)
        id(NAVIGATION_SAFEARGS_KOTLIN)
        id(ANDROID_EXTENSIONS)
    }
}
androidFeature()
dependencies {
    implementation(project(Modules.DOMAIN))
    implementation(project(Modules.Utility.BASES))
    implementation(project(Modules.NAVIGATION))
}