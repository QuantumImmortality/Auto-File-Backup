package Config

/**
 * Container for the different configuration options of the system
 */
data class Configuration(val config: Map<String, Any?>) {
    val backupDir: String by config
    val debug: Boolean by config
    val compression: Boolean by config
}