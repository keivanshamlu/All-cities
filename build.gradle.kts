buildscript {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.20")
        GradlePlugins.run {
            classpath(ANDROID_GRADLE)
            classpath(KOTLIN_GRADLE)
            classpath(SAFE_ARGS)
            classpath("com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:2.0.0")
        }
    }
}

allprojects {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}