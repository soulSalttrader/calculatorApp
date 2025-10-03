package com.example.calculatorApp.utils

import kotlin.reflect.KClass

object TestUtils {

    fun <T : Any> provideSequenceOfSingletons(
        type: KClass<out T>
    ): Sequence<T> = type.sealedSubclasses
            .asSequence()
            .map { subclass ->
                requireNotNull(subclass.objectInstance) {
                    "Expected object singleton in sealed hierarchy of ${type.simpleName}, but found class: ${subclass.simpleName}"
                }
            }
}