package ru.loggerlib.entity

data class ExtendedLogObj(
    val msg: String = "",
    val e: Throwable? = null,
    val objs: Map<String, Any> = emptyMap(),
) : LogObj() {
    override fun toJson(): String = buildString {
        append("\n{\n")
        append("\tLogging Message: $msg\n")
        e?.let { error ->
            append("\tError Message: ${error.message}")
        }
        objs.forEach { (key, value) ->
            append("\t$key: $value\n")
        }
        append("}\n")
    }
}