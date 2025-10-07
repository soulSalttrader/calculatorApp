package com.example.calculatorApp.utils

import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties

object PrettyPrinter {

    private val cache: MutableMap<KClass<*>, List<KProperty1<Any, *>>> = mutableMapOf()

    private fun getProperties(clazz: KClass<*>): List<KProperty1<Any, *>> =
        cache.getOrPut(clazz) {
            clazz.memberProperties.map { it as KProperty1<Any, *> }
        }

    fun toPrettyString(target: Any): String {
        val props = getProperties(target::class)
            .joinToString(", ") { prop ->
                runCatching { "${prop.name}=${prop.get(target)}" }
                    .getOrElse { "${prop.name}=<error>" }
            }
        return "${target::class.simpleName}($props)"
    }

    fun Any.prettyString(): String = toPrettyString(this)
}