package GetListDirectories

import Logging.LogLevel
import Logging.Logger.Companion.writeLogMessage
import java.io.File

internal class LoadDirectories {

    private var fileName = "Directories.txt"
    /**
     * If file doesn't exist, create it
     */
    fun createFile(){
        var file = File(fileName)
        file.createNewFile()
    }

    /**
     * Load the file containing the list of directories to backup
     * separated by new lines
     * @return List of the directories
     */
    fun loadFile(): List<String> {

        val dirs: MutableList<String> = mutableListOf()

        val file = File(fileName)

        if(!file.exists())
            writeLogMessage("Directories file doesn't exist", LogLevel.ERROR)
        if(file.length() == 0L)
            writeLogMessage("Directories file doesn't contain any directories to backup", LogLevel.WARN)

        file.forEachLine { dirs.add(it) }

        return dirs
    }
}