package groupDepsModuleLevel

import androidDeps.groupDeps.*
import kotlinDeps.groupDeps.kotlinCoroutines
import org.gradle.api.artifacts.dsl.DependencyHandler
import testDeps.groupDeps.test
import testDeps.groupDeps.testAndroid

fun DependencyHandler.baseDependencies() {
    kotlinCoroutines()
    koinKotlin()
    test()
    arch()
}
fun DependencyHandler.baseAndroidDependencies() {
    baseDependencies()
    koinAndroid()
    testAndroid()
}
fun DependencyHandler.featureModuleBaseDependencies() {
    androidDefault()
    fragment()
    lifeCycle()
    arch()
    naviagtion()
    databinding()
    multidex()
    daggerAssisted()
}