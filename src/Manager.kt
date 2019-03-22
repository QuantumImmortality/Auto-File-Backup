import Config.ConfigManager
import Config.Configuration
import GetListDirectories.*
import Logging.*

/**
 * Controller class of the application
 */

//Load system config
val configManager = ConfigManager()
val configs: Configuration = configManager.loadConfig()

fun main(){

    val dir = DirectoryManager()
    dir.createFile()

    val dirs = dir.loadFile()

    if(configs.debug) {
        dirs.forEach {
            Logger.writeLogMessage(
                "Copy was successful: " + dir.copyDir(it, configs.backupDir, configs.compression) + "\n\t" + it,
                LogLevel.DEBUG
            )
        }
    } else
        dirs.forEach {
             dir.copyDir(it, configs.backupDir, configs.compression)
        }
}

//TODO DEBUG To file
//TODO Overwrite with more functionality, keep last two by default, put into old dir, then push upper level one into old, overwriting the old one, and put fresh in upper dir
//TODO handle when configs set wrong