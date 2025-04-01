package com.example.calculatorApp.utils

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import kotlin.reflect.KClass

object OperatorList {

    private fun provideSequenceOperator(operatorClass: KClass<out Operator>): Sequence<Operator> {
        return try {
            operatorClass.sealedSubclasses
                .asSequence()
                .mapNotNull { it.objectInstance }
        } catch (e: Exception) {
            throw IllegalArgumentException("Unknown button class: $operatorClass.")
        }
    }

    val operatorBinary = provideSequenceOperator(OperatorBinary::class)

    val allOperators: Sequence<Operator> = sequenceOf(
        operatorBinary,
    ).flatMap { it }
}