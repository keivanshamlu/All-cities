package kotlinDeps.groupDeps

import kotlinDeps.NetworkDeps

import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.implementation
import utility.testImplementation

fun DependencyHandler.networking() {
    NetworkDeps.OkHttp.run {
        implementation(core)
        implementation(logger)
        testImplementation(test)
    }
    NetworkDeps.Retrofit.run {
        implementation (core)
    }
}