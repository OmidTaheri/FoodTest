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
        const val FRAGMENT_KTX = "1.3.6"
        const val LIVEDATA_KTX = "2.2.0"
        const val LIFECYCLE = "2.3.1"
        const val ROOM = "2.3.0"
        const val NAVIGATION = "2.3.5"
        const val PAGING = "3.0.1"
    }


    object UiDependenciesVersions {
        const val APPCOMPAT = "1.3.1"
        const val MATERIAL = "1.4.0"
        const val RECYCLE_VIEW = "1.2.1"
        const val CONSTRAINT_LAYOUT = "2.1.1"
        const val CARDVIEW = "1.0.0"
        const val VIEWPAGER2 = "1.0.0"
        const val VECTORE_DRAWABLE = "1.1.0"
        const val COORDINATOR_LAYOUT = "1.1.0"
        const val ANIMATED_VECTORE_DRAWABLE = "1.1.0"
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