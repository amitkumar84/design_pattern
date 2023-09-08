package com.amitsah.designpatterns.low_level_design.logging_system

abstract class Logger constructor(private var logger: Logger?) {
    open fun log(logLevel: Int, message: String?) {
        logger?.log(logLevel, message)
    }

    companion object {
        var INFO = 1
        var DEBUG = 2
        var ERROR = 3
    }
}

class DebugLogger constructor(nexLogger: Logger?) :
    Logger(nexLogger) {
    override fun log(logLevel: Int, message: String?) {
        if (logLevel == DEBUG) {
            println("DEBUG: $message")
        } else {
            super.log(logLevel, message)
        }
    }
}

class ErrorLogger constructor(nexLogger: Logger?) :
    Logger(nexLogger) {
    override fun log(logLevel: Int, message: String?) {
        if (logLevel == ERROR) {
            println("ERROR: $message")
        } else {
            super.log(logLevel, message)
        }
    }
}


class InfoLogger constructor(nexLogger: Logger?) :
    Logger(nexLogger) {
    override fun log(logLevel: Int, message: String?) {
        if (logLevel == INFO) {
            println("INFO: $message")
        } else {
            super.log(logLevel, message)
        }
    }
}

fun main() {
    val logObject: Logger = InfoLogger(DebugLogger(ErrorLogger(null)))

    logObject.log(Logger.ERROR, "exception happens")
    logObject.log(Logger.DEBUG, "need to debug this ")
    logObject.log(Logger.INFO, "just for info ")

}

