package Logging

/**
 * Container for logging facilities
 */
class Logger{
    companion object {

        /**
         * Print the [statement] to console with the respective
         * [logLevel] tag
         */
        fun writeLogMessage(statement: String, logLevel: LogLevel) {
            println("[$logLevel]: $statement")
        }
    }
}