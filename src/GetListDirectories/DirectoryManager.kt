package GetListDirectories

import Logging.LogLevel
import Logging.Logger.Companion.writeLogMessage
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

/**
 * Manage all things to do with file or directory manipulation
 */
class DirectoryManager {

    private var fileName = "Directories.txt"

    /**
     * If file doesn't exist, create it
     */
    fun createFile(){
        val file = File(fileName)
        file.createNewFile()
    }

    /**
     * Load the file containing the list of directories to backup
     * separated by new lines. Returns the list of the directories
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
     * Copy the [target] directory or file to the [destination]. The
     * copied files will be compressed if [compression] is enabled
     * and returns if the copy was successful
     */
    fun copyDir(target: String, destination: String, compression: Boolean): Boolean {

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

        val isDir = targetFile.isDirectory
        val splitTarget: List<String> = target.split("\\")
        val success: Boolean
        val zipDestination: String

        if(compression){
            zipDestination = when(isDir){
                true -> destination + "\\" + splitTarget.last() + ".zip"
                else -> destination + "\\" + splitTarget.last().substring(0, splitTarget.last().indexOf(".")) + ".zip"
            }

            success = zipFile(target, zipDestination)
        } else{
            destinationDir = File(destination + "\\" + splitTarget.last())

            if(isDir)
                destinationDir.mkdir()

            success = targetFile.copyRecursively(destinationDir, true)
        }

        return success
    }

    /**
     * If compression is set true, then zip the copied
     * files from the [target] location to the [destination] instead
     * and returns if the copy was successful
     */
    fun zipFile(target: String, destination: String): Boolean {
        println("T $target D $destination")

        //Remove previous backup
        File(destination).let { if (it.exists()) it.delete() }

        val zipFile = Files.createFile(Paths.get(destination))

        ZipOutputStream(Files.newOutputStream(zipFile)).use { stream ->
            val sourceDir = Paths.get(target)

            //Walk through and copy individual files from sub directories
            Files.walk(sourceDir).filter { path -> !Files.isDirectory(path) }.forEach { path ->

                val zipEntry = ZipEntry(path.toString().substring(sourceDir.toString().length))

                stream.putNextEntry(zipEntry)
                stream.write(Files.readAllBytes(path))
                stream.closeEntry()
            }
        }

        //Check if the zip exists at target location
        return File(destination).exists()
    }
}