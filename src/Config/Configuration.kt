package Config

data class Configuration(val config: Map<String, Any?>) {
    val backupDir: String by config
    val debug: Boolean by config
}