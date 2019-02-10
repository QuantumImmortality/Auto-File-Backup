import Config.ConfigManager
import Config.Configuration
import GetListDirectories.*
import Logging.Logger

fun main(args : Array<String>){

    val configManager = ConfigManager()
    val configs: Configuration = configManager.loadConfig()

    println(configs.backupDir)

    var dir = LoadDirectories()
    dir.createFile()

    //Logger.writeLogMessage("logAStatment", Logging.LogLevel.DEBUG)
    var dirs = dir.loadFile()

    dirs.forEach{
        println(it)
        //dir.copyDir(it)
    }



}
/*
TODO Read txt file with each line have an explicit dir of file to copy
TODO Target dir to put the copy, initially put to same, then separate folders
TODO DO I want it to be just duplicate a whole DIR or specific files?

 */