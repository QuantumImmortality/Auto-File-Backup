package Config

import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import Logging.Logger

/**
 * Load the systems configurations from file
 */
class ConfigManager{

    /**
     * Get the configuration values from the config file
     */
    fun loadConfig(): Configuration{
        val lines = Files.readAllLines(Paths.get("Configuration.txt"), StandardCharsets.UTF_8)
        val keyValuePairs = lines.map{ it.trim() }
            .filterNot { it.isEmpty() }
            .map(::toKeyValuePair)

        val configurationMap = hashMapOf<String, Any>()

        keyValuePairs.forEach{
            when(it.first) {
                "BACKUPDIR" -> configurationMap.put("backupDir", it.second)
                "DEBUG" -> configurationMap.put("debug", it.second.toBoolean())
                "COMPRESSION" -> configurationMap.put("compression", it.second.toBoolean())
                else -> Logger.writeLogMessage("Encountered unexpected key ${it.first}=${it.second}", Logging.LogLevel.WARN)
            }
        }

        return Configuration(configurationMap)
    }

    /**
     * Used to determine if a [line] is a comment or target data
     */
    private fun isComment(line: String) = line.startsWith("#")

    /**
     * Split the values from the variable name in the [line], if their is no values after
     * the variable name, it is returned as an empty String
     */
    private fun toKeyValuePair(line: String) = line.split(Regex(" "), 2).let {
        Pair(it[0], if (it.size == 1) "" else it[1])
    }
}