interface BuildTypes {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
    val debuggable: Boolean

}

object BuildTypeDebug : BuildTypes {
    override val isMinifyEnabled = false
    override val debuggable = true

    const val APPLICATION_ID_SUFFIX = ".debug"
    const val VERSION_SUFFIX = "-DEBUG"
}


object BuildTypeRelease : BuildTypes {
    override val isMinifyEnabled = true
    override val debuggable = false
}
