import GetListDirectories.*
import Logging.Logger

fun main(args : Array<String>){

    var dir = LoadDirectories()
    dir.createFile()

    //Logger.writeLogMessage("logAStatment", Logging.LogLevel.DEBUG)
    var dirs = dir.loadFile()

    dirs.forEach{println(it)}

}
/*
TODO Read txt file with each line have an explicit dir of file to copy
TODO Target dir to put the copy, initially put to same, then separate folders

 */