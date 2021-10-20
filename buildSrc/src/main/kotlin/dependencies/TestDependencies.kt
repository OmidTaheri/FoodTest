package dependencies
object TestDependencies {
    const val JUNIT =
        "junit:junit:${DependenciesVersions.TestDependenciesVersions.JUNIT}"

    const val EXT_JUNIT =
        "androidx.test.ext:junit:${DependenciesVersions.TestDependenciesVersions.JUNIT_EXT}"

    const val EXT_TRUTH =
        "androidx.test.ext:truth:${DependenciesVersions.TestDependenciesVersions.TRUTH_EXT}"

    const val TRUTH =
        "com.google.truth:truth:${DependenciesVersions.TestDependenciesVersions.TRUTH}"

    const val CORE =
        "androidx.test:core:${DependenciesVersions.TestDependenciesVersions.TEST}"

    const val MOCKK =
        "io.mockk:mockk:${DependenciesVersions.TestDependenciesVersions.MOCKK}"

    const val ROBOELECTRIC =
        "org.robolectric:robolectric:${DependenciesVersions.TestDependenciesVersions.ROBOLECTRIC}"

    const val ESPRESSO =
        "androidx.test.espresso:espresso-core:${DependenciesVersions.TestDependenciesVersions.ESPRESSO}"
}