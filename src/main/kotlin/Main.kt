package ru.loggerlib

import ru.loggerlib.entity.BaseLogObj
import ru.loggerlib.entity.LogObj

fun main() {
    println("LOGGER LIB")
    val logger = getLogger(String::class.java)
    val logObj: LogObj = BaseLogObj(
        "TEST LOG MESSAGE",
        mapOf("Module name:" to "Logger Lib")
    )
    logger.info(logObj)
}