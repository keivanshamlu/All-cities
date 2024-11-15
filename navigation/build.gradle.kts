import androidDeps.groupDeps.naviagtion
import androidDeps.groupDeps.room
import configs.androidLib
import kotlinDeps.groupDeps.gson
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        kotlin(ANDROID)
        kotlin(KAPT)
        id(NAVIGATION_SAFEARGS_KOTLIN)
        id("kotlin-parcelize")
    }
}
androidLib()
dependencies {
    implementation(project(Modules.Data.DATA))
    implementation(project(Modules.Utility.BASES))
    gson()
    naviagtion()
}
