package ru.loggerlib.entity

import kotlinx.serialization.Serializable

@Serializable
sealed class LogObj {
    abstract fun toJson(): String
}