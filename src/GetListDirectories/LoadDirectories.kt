package GetListDirectories

import java.io.File

internal class LoadDirectories {

    fun createFile(){

        val fileName = "Directories.txt"
        var file = File(fileName)
        file.createNewFile()

    }
}