package ru.loggerlib

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import ru.loggerlib.config.LogLevel
import ru.loggerlib.entity.BaseLogObj
import ru.loggerlib.entity.ExtendedLogObj
import ru.loggerlib.entity.LogObj
import java.awt.Color

fun <T> getLogger(clazz: Class<T>): CommonLogger = CommonLogger(clazz = clazz)

class CommonLogger(
    private val clazz: Class<*> = CommonLogger::class.java,
    private val logger: Logger = LoggerFactory.getLogger(clazz)
) : ILogWrapper {
    override fun log(logObj: LogObj, logLevel: LogLevel) = logByLogLevel(logObj, logLevel)

    private fun logByLogLevel(logObj: LogObj, logLevel: LogLevel) = when (logLevel) {
        LogLevel.ERROR -> logger.error(logObj.toJson().toRed())
        LogLevel.WARN -> logger.warn(logObj.toJson().toYellow())
        LogLevel.INFO -> logger.info(logObj.toJson().toGreen())
        LogLevel.DEBUG -> logger.debug(logObj.toJson().toBlue())
        LogLevel.TRACE -> logger.trace(logObj.toJson().toBlue())
    }

    private fun String.toRed() = "#fc0303$this#fc0303"
    private fun String.toYellow() = "#f0fc03$this#f0fc03"
    private fun String.toGreen() = "#00ff3c$this#00ff3c"
    private fun String.toBlue() = "#0800ff$this#0800ff"
}

