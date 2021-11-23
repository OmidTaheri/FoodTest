package dependencies
object AnnotationProcessorsDependencies {

    const val DAGGER =
        "com.google.dagger:dagger-compiler:${DependenciesVersions.GeneralDependenciesVersions.DAGGER}"

    const val ROOM =
        "androidx.room:room-compiler:${DependenciesVersions.JetpackDependenciesVersions.ROOM}"

    const val GLIDE_COMPILER =
     "com.github.bumptech.glide:compiler:${DependenciesVersions.GeneralDependenciesVersions.GLIDE}"

    const val LIFECYCLE =
        "androidx.lifecycle:lifecycle-compiler:${DependenciesVersions.JetpackDependenciesVersions.LIFECYCLE}"
}