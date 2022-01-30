import groupDepsModuleLevel.baseDependencies

plugins {
    GradlePluginId.apply {
        id(JAVA_LIBRARY)
        id(KOTLIN)
    }
}
java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
dependencies {

    baseDependencies()
}