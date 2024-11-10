import configs.androidLib
import kotlinDeps.groupDeps.gson
import modules.Modules

plugins {
    GradlePluginId.apply {
        id(ANDROID_LIBRARY)
        kotlin(ANDROID)
        kotlin(KAPT)
        id(NAVIGATION_SAFEARGS_KOTLIN)
    }
}
androidLib{
    buildConfigField("String", "CITIES_FILE_NAME", "\"cities.txt\"")
}
dependencies {
    implementation(project(Modules.Data.DATA))
    implementation(project(Modules.Utility.BASES))
    implementation(project(Modules.Utility.BASES_ANDROID))
    gson()
}
