import androidDeps.AppConfig
import androidDeps.groupDeps.room
import groupDepsModuleLevel.featureModuleBaseDependencies
import kotlinDeps.groupDeps.networking
import modules.Modules
import configs.androidApp

plugins {
    GradlePluginId.apply {
        id(ANDROID_APPLICATION)
        kotlin(ANDROID)
        kotlin(KAPT)
    }
}
androidApp(AppConfig.applicationId)
dependencies {

    Modules.run {
        implementation(project(DOMAIN))
        implementation(project(Modules.Utility.BASES))
    }
    Modules.Data.run {
        implementation(project(DATA))
        implementation(project(DATA_LOCAL))
    }
    Modules.Feature.run {
        implementation(project(FEATURE_ONE))
    }
    featureModuleBaseDependencies()
    room()
    networking()
}