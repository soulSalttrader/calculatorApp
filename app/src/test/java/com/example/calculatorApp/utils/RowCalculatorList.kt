package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers

object RowCalculatorList {
    val buttonsBinaryTest = binary.map { ButtonData(it) }
    val buttonsControlsTest = controls.map { ButtonData(it) }
    val buttonsNumbersTest = numbers.map { ButtonData(it) }

    val standards: Array<RowCalculatorStandard> = RowCalculatorStandard::class.sealedSubclasses
        .mapIndexedNotNull { index, subclass ->
            val buttons = if (index % 2 == 0) buttonsBinaryTest else buttonsNumbersTest
            runCatching { subclass.constructors.first().call(buttons) }.getOrNull()
        }
        .toTypedArray()
}