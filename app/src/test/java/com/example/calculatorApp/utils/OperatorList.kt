package com.example.calculatorApp.utils

import com.example.calculatorApp.domain.ast.OperatorBinary

object OperatorList {

    val operatorBinary : Array<OperatorBinary> = OperatorBinary::class.sealedSubclasses
        .mapNotNull { it.objectInstance }
        .toTypedArray()
}