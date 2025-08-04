package com.example.calculatorApp.utils

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.utils.TestUtils.provideSequenceOfSingletons

object OperatorList {

    val operatorBinary = provideSequenceOfSingletons(OperatorBinary::class)
    val allOperators: Sequence<Operator> = sequenceOf(operatorBinary).flatMap { it }
}