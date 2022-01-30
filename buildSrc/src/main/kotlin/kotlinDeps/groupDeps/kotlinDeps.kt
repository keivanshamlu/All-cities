package kotlinDeps.groupDeps

import kotlinDeps.KotlinDeps
import org.gradle.api.artifacts.dsl.DependencyHandler
import utility.implementation
import utility.testImplementation


fun DependencyHandler.kotlinCoroutines() {
    KotlinDeps.run {
        implementation (kotlin_stdlib)
        implementation (KotlinDeps.Coroutine.core)
        implementation (KotlinDeps.Coroutine.coroutine)
        testImplementation (KotlinDeps.Coroutine.test)
    }
}
