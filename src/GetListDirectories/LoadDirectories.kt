package GetListDirectories

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

    fun loadFile(): List<String> {

        val dirs: MutableList<String> = mutableListOf()

        val file = File(fileName)
        file.forEachLine { dirs.add(it) }

        return dirs
    }
}