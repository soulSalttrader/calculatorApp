package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.utils.ButtonCalculatorList.arithmetics
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers

object RowCalculatorList {
    val buttonsArithmeticsTest = arithmetics.map { ButtonData(it) }
    val buttonsControlsTest = controls.map { ButtonData(it) }
    val buttonsNumbersTest = numbers.map { ButtonData(it) }

    val standards: Array<RowCalculatorStandard> = RowCalculatorStandard::class.sealedSubclasses
        .mapIndexedNotNull { index, subclass ->
            val buttons = if (index % 2 == 0) buttonsArithmeticsTest else buttonsNumbersTest
            runCatching { subclass.constructors.first().call(buttons) }.getOrNull()
        }
        .toTypedArray()
}