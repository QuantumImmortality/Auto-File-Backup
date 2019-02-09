package Logging

class Logger{
    companion object {
        fun writeLogMessage(statement: String, logLevel: LogLevel) {
            println("[$logLevel]: $statement")
        }
    }
}