package ru.loggerlib

import kotlinx.serialization.Serializable
import ru.loggerlib.config.LogLevel

@Serializable
sealed class LogObj

@Serializable
data class BasicLoggingObj(
    val msg: String = "",
    val objs: Map<String, String>? = null,
) : LogObj()

data class ExtendedLoggingObj(
    val msg: String = "",
    val e: Throwable? = null,
    val data: Any? = null,
    val objs: Map<String, Any>? = null,
) : LogObj()

interface ILogWrapper {
    fun log(
        logObj: LogObj,
        logLevel: LogLevel = LogLevel.TRACE,
    )

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
                ExtendedLoggingObj(defaultMsg, e)
            } else {
                ExtendedLoggingObj(e = e)
            }

        log(logObj, LogLevel.ERROR)
        if (throwRequired) throw e
        else null
    }
}