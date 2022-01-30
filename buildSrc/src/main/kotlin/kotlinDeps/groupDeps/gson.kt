package kotlinDeps.groupDeps

import org.gradle.api.artifacts.dsl.DependencyHandler
import kotlinDeps.GsonDeps
import utility.implementation

fun DependencyHandler.gson() {
    
    GsonDeps.run {
        implementation (gsonConverter)
    }
}