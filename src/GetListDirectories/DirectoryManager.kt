package GetListDirectories

import Logging.LogLevel
import Logging.Logger.Companion.writeLogMessage
import java.io.File

internal class DirectoryManager {

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

    /**
     * Copy the target directory or file to the destination
     * @param target The target file or dir to copy
     * @param destination The destination Dir to copy the target to
     * @return If the copy was successful
     */
    fun copyDir(target: String, destination: String): Boolean {

        val targetFile = File(target)

        if(!targetFile.exists()) {
            writeLogMessage("$target target does not exist!", LogLevel.WARN)
            return false
        }
        var destinationDir = File(destination)

        if(!destinationDir.exists()){
            writeLogMessage("$destination backup directory does not exist! Making directory", LogLevel.WARN)
            destinationDir.mkdir()
        }

        val splitTarget: List<String> = target.split("\\")
        destinationDir = File(destination + "\\" + splitTarget.last())

        if(targetFile.isDirectory)
            destinationDir.mkdir()

        return targetFile.copyRecursively(destinationDir, true)
    }

    fun zipFile(target: String, destination: String){

    }
}