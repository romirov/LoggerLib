package ru.loggerlib

import ru.loggerlib.config.LogLevel
import ru.loggerlib.entity.ExtendedLogObj
import ru.loggerlib.entity.LogObj

interface ILogWrapper {
    fun log(logObj: LogObj, logLevel: LogLevel = LogLevel.TRACE)

    fun error(logObj: LogObj) = log(logObj, LogLevel.ERROR)

    fun warn(logObj: LogObj) = log(logObj, LogLevel.WARN)

    fun info(logObj: LogObj) = log(logObj, LogLevel.INFO)

    fun debug(logObj: LogObj) = log(logObj, LogLevel.DEBUG)

    fun trace(logObj: LogObj) = log(logObj, LogLevel.TRACE)

    suspend fun <T> doWithErrorLogging(
        throwRequired: Boolean = true,
        defaultMsg: String,
        block: suspend () -> T,
    ): T? = try {
        block()
    } catch (e: Throwable) {
        val logObj =
            if (defaultMsg.isNotBlank()) {
                ExtendedLogObj(defaultMsg, e)
            } else {
                ExtendedLogObj(e = e)
            }

        log(logObj, LogLevel.ERROR)
        if (throwRequired) throw e
        else null
    }
}