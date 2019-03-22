package Logging

import configs
import java.io.File
import java.io.FileOutputStream
import java.util.*

/**
 * Container for logging facilities
 */
class Logger{
    companion object {
        private const val fileName = "log.log"

        /**
         * Print the [statement] to console with the respective
         * [logLevel] tag
         */
        fun writeLogMessage(statement: String, logLevel: LogLevel) {
            if(configs.logToFile)
                writeToFile("[$logLevel]: $statement")
            else
                println("[$logLevel]: $statement")
        }

        fun writeToFile(logMsg: String){

            val file = File(fileName)
            val date = Date()

            FileOutputStream(file, true).bufferedWriter().use { writer -> writer.write("$date $logMsg \r\n") }
        }
    }
}