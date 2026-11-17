object BuildConfig {
    const val MINECRAFT_VERSION: String = "26.1"
    const val FABRIC_LOADER_VERSION: String = "0.18.4"
    const val FABRIC_API_VERSION: String = "0.144.3+26.1"
    const val UKULIB_VERSION: String = "2.0.0+26.1"

    const val MOD_VERSION: String = "2.6.0"

    const val MODRINTH_PROJECT_ID: String = "1qgfBAoj"

    fun createVersionString(): String {
        return "$MOD_VERSION+mc$MINECRAFT_VERSION"
    }
}