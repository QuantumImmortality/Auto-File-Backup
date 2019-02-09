import GetListDirectories.*
import Logging.Logger

fun main(args : Array<String>){

    var dir = LoadDirectories()
    dir.createFile()

    Logger.writeLogMessage("logAStatment", Logging.LogLevel.DEBUG)

}
