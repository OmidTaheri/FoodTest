buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("${BuildPluginsClasspath.ANDROID_GRADLE_PLUGIN}")
        classpath("${BuildPluginsClasspath.KOTLIN_GRADLE_PLUGIN}")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}