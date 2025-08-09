package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.utils.TestUtils.provideSequenceConstructed
import com.example.calculatorApp.utils.TestUtils.provideSequenceOfSingletons

object TestDataElementSeq {

    // Buttons
    val buttonsBinaryTest = provideSequenceOfSingletons(ButtonCalculatorBinary::class)
    val buttonsUnaryTest = provideSequenceOfSingletons(ButtonCalculatorUnary::class)
    val buttonsControlsTest = provideSequenceOfSingletons(ButtonCalculatorControl::class)
    val buttonsNumbersTest = provideSequenceOfSingletons(ButtonCalculatorNumber::class)
    val buttonsParenthesisTest = provideSequenceOfSingletons(ButtonCalculatorParenthesis::class)

    val buttonsAllTest: Sequence<Button> = sequenceOf(
        buttonsBinaryTest,
        buttonsUnaryTest,
        buttonsControlsTest,
        buttonsNumbersTest,
        buttonsParenthesisTest,
    ).flatMap { it }

    // Displays
    val displaysInputsTest = provideSequenceOfSingletons(DisplayCalculatorInput::class)

    // Rows
    val buttonDataBinaryTest = buttonsBinaryTest.map { ButtonData(it) }
    val buttonDataControlsTest = buttonsControlsTest.map { ButtonData(it) }
    val buttonDataNumbersTest = buttonsNumbersTest.map { ButtonData(it) }

    val rowsStandardTest = provideSequenceConstructed(
        type = RowCalculatorStandard::class,
        constructorArgs = TestDataElementExpectedMap.standardExpectedMapTest,
    )

    // Operators
    val operatorsBinaryTest = provideSequenceOfSingletons(OperatorBinary::class)
    val operatorsAllTest: Sequence<Operator> = sequenceOf(operatorsBinaryTest).flatMap { it }
}