package ru.loggerlib.entity

import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class BaseLogObj(
    val msg: String = "",
    val objs: Map<String, String> = emptyMap(),
) : LogObj() {
    override fun toJson(): String = buildString {
        append("\n{\n")
        append("\tLogging Message: $msg\n")
        objs.forEach { (key, value) ->
            append("\t$key: $value\n")
        }
        append("}\n")
    }
}