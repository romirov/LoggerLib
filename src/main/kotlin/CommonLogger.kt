package ru.loggerlib

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.loggerlib.config.LogLevel

fun <T> getLogger(clazz: Class<T>): CommonLogger = CommonLogger(clazz = clazz)

class CommonLogger(
    private val clazz: Class<*> = CommonLogger::class.java,
    private val logger: Logger = LoggerFactory.getLogger(clazz)
) : ILogWrapper {
    override fun log(logObj: LogObj, logLevel: LogLevel) {
        when (logObj::class.java) {
            BasicLoggingObj::class.java -> logByLogLevel(logObj, logLevel)
            ExtendedLoggingObj::class.java -> logByLogLevel(logObj, logLevel)
        }
    }

    private fun logByLogLevel(logObj: LogObj, logLevel: LogLevel) = when (logLevel) {
        LogLevel.ERROR -> logger.error(Json.encodeToString(logObj))
        LogLevel.WARN -> logger.warn(Json.encodeToString(logObj))
        LogLevel.INFO -> logger.info(Json.encodeToString(logObj))
        LogLevel.DEBUG -> logger.debug(Json.encodeToString(logObj))
        LogLevel.TRACE -> logger.trace(Json.encodeToString(logObj))
    }
}