import dependencies.DependenciesVersions

object BuildPluginsClasspath {
    const val ANDROID_GRADLE_PLUGIN =
        "com.android.tools.build:gradle:${DependenciesVersions.GradlePluginsVersions.ANDROID_GRADLE_PLUGIN}"
    const val KOTLIN_GRADLE_PLUGIN =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${DependenciesVersions.GradlePluginsVersions.KOTLIN_GRADLE_PLUGIN}"
    const val SAFEARGS_PLUGIN =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${DependenciesVersions.GradlePluginsVersions.SAFEARGS}"
}