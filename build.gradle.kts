buildscript {
    repositories {
        mavenCentral()
        google()
        mavenLocal()
    }
    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
        GradlePlugins.run {
            classpath(ANDROID_GRADLE)
            classpath(KOTLIN_GRADLE)
            classpath(SAFE_ARGS)
            classpath(GOOGLE_SECRETS)
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
