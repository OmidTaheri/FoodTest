package dependencies

object DependenciesVersions {


    object GeneralDependenciesVersions {
        const val RX_Kotlin = "3.0.1"
        const val RX_ANDROID = "3.0.0"
        const val RX_JAVA = "3.1.2"
        const val GLIDE = "4.11.0"
        const val DAGGER = "2.39.1"
        const val ANNOTATION = "1.1.0"
        const val JAVAX_INJECT = "1"
        const val JAVAX_ANNOTATION = "1.0"
        const val MULTIDEX = "2.0.1"
    }

    object JetpackDependenciesVersions {
        const val CORE_KTX = "1.6.0"
        const val ROOM = "2.3.0"
        const val NAVIGATION = "2.4.0-alpha04"
        const val FRAGMENT = "1.4.0"
    }


    object UiDependenciesVersions {
        const val APPCOMPAT = "1.4.0"
        const val ACTIVITY = "1.4.0"
        const val MATERIAL = "1.4.0"
        const val CONSTRAINT_LAYOUT = "2.1.1"
        const val DRAWER_LAYOUT = "1.1.1"
        const val RECYCLER_VIEW = "1.2.1"
    }

    object TestDependenciesVersions {
        const val TEST = "1.4.0"
        const val JUNIT_EXT = "1.1.3"
        const val TRUTH_EXT = "1.4.0"
        const val TRUTH = "1.1.3"
        const val JUNIT = "4.12"
        const val ROBOLECTRIC = "4.6.0"
        const val MOCKK = "1.12.0"
        const val ESPRESSO = "3.4.0"
    }

    object GradlePluginsVersions {
        const val ANDROID_GRADLE_PLUGIN = "7.0.3"
        const val KOTLIN_GRADLE_PLUGIN = "1.5.31"
        const val SAFEARGS = JetpackDependenciesVersions.NAVIGATION
    }

}