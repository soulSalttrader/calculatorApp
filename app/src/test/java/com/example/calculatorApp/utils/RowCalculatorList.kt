package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers
import com.example.calculatorApp.utils.RowCalculatorMappings.standardVisualsMap
import com.example.calculatorApp.utils.TestUtils.provideSequenceConstructed

object RowCalculatorList {

    val buttonsBinaryTest = binary.map { ButtonData(it) }
    val buttonsControlsTest = controls.map { ButtonData(it) }
    val buttonsNumbersTest = numbers.map { ButtonData(it) }

    val standardRows = provideSequenceConstructed(
        type = RowCalculatorStandard::class,
        constructorArgs = standardVisualsMap,
    )
}