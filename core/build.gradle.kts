import androidDeps.groupDeps.glide
import androidDeps.groupDeps.room
import configs.androidLib
import groupDepsModuleLevel.baseAndroidDependencies
import kotlinDeps.groupDeps.networking
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
androidLib {
    buildConfigField("String", "BASE_URL", "\"https://jsonplaceholder.typicode.com\"")
}
dependencies {
    implementation(project(Modules.Data.DATA_LOCAL))
    implementation(project(Modules.Utility.BASES))

    baseAndroidDependencies()
    room()
    networking()
    glide()
}