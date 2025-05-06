package com.averito.mimi.core.utils

interface AppLogger {
    fun debug(message: String): Unit
    fun info(message: String): Unit
    fun warn(message: String): Unit
    fun error(message: String): Unit
}