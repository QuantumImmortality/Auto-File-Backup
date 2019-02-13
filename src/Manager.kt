import Config.ConfigManager
import Config.Configuration
import GetListDirectories.*
import Logging.*

fun main(args : Array<String>){

    val configManager = ConfigManager()
    val configs: Configuration = configManager.loadConfig()

    var dir = DirectoryManager()
    dir.createFile()

    var dirs = dir.loadFile()

    if(configs.debug) {
        dirs.forEach {
            Logger.writeLogMessage(
                "Copy was successful: " + dir.copyDir(it, configs.backupDir) + "\n\t" + it,
                LogLevel.DEBUG
            )
        }
    } else
        dirs.forEach {
             dir.copyDir(it, configs.backupDir)
        }
}


//TODO Make into a jar or w/e kotlin's equiv is?
//TODO Add capacity for compression?