package com.example.calculatorApp.utils

import com.example.calculatorApp.testData.expected.ExpectedElement
import kotlin.reflect.KClass
import kotlin.reflect.KParameter

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

    fun <T : Any, R> provideSequenceConstructed(
        type: KClass<out T>,
        constructorArgs: Map<KClass<out T>, R>,
    ): Sequence<T> = type.sealedSubclasses
        .asSequence()
        .map { subclass ->
            subclass.requireSingleConstructor()
            val constructor = subclass.constructors.first()

            val params = constructor.parameters
            params.requireSingleSequenceParameter(subclass)

            val args = constructorArgs[subclass] ?: error("No constructor args provided for ${subclass.simpleName}")

            val buttonData = when (args) {
                is ExpectedElement.Row -> args.buttonData
                else -> error("Unknown args $args")
            }

            constructor.call(buttonData)
        }

    fun <T : Any, R> mapSingletonsTo(
        type: KClass<out T>,
        items: Sequence<T> = provideSequenceOfSingletons(type),
        mapping: (T) -> R,
    ): Sequence<R> = items.map(mapping)

    private fun <T : Any> List<KParameter>.requireSingleSequenceParameter(subclass: KClass<out T>) = require(
        this.size == 1 && this.first().type.classifier == Sequence::class) {
        "Constructor of ${subclass.simpleName} must accept a single Sequence parameter"
    }

    private fun <T : Any> KClass<out T>.requireSingleConstructor() = require(
        this.constructors.size == 1) {
        "Expected exactly one constructor for ${this.simpleName}, found ${this.constructors.size}"
    }
}