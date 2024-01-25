package ru.loggerlib

fun main() {
    println("LOGGER LIB")
    val logger = getLogger(String::class.java)
    val logObj: LogObj = BasicLoggingObj(
        "TEST LOG MESSAGE",
        mapOf("Module name:" to "Logger Lib")
    )
    logger.info(logObj)
}