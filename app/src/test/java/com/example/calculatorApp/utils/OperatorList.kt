//package com.example.calculatorApp.utils
//
//import com.example.calculatorApp.domain.ast.Operator
//import com.example.calculatorApp.domain.ast.OperatorBinary
//import com.example.calculatorApp.utils.TestUtils.provideSequenceOfSingletons
//
//object OperatorList {
//
//    val operatorsBinaryTest = provideSequenceOfSingletons(OperatorBinary::class)
//    val operatorsAllTest: Sequence<Operator> = sequenceOf(operatorsBinaryTest).flatMap { it }
//}