package ru.loggerlib.config

enum class LogLevel(
    private val levelInt: Int,
    private val levelStr: String,
) {
    ERROR(40, "ERROR"),
    WARN(30, "WARN"),
    INFO(20, "INFO"),
    DEBUG(10, "DEBUG"),
    TRACE(0, "TRACE");

    /**
     * Returns the int representation of this Level.
     */
    @Suppress("unused")
    fun toInt(): Int {
        return levelInt
    }

    /**
     * Returns the string representation of this Level.
     */
    override fun toString(): String {
        return levelStr
    }
}