package com.example.calculatorApp.testData

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.OperatorParenthesis
import com.example.calculatorApp.domain.ast.OperatorUnary
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.button.ButtonCategory
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayCategory
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
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
    val operatorsUnaryPrefixTest = provideSequenceOfSingletons(OperatorUnary.Prefix::class)
    val operatorsUnarySuffixTest = provideSequenceOfSingletons(OperatorUnary.Suffix::class)
    val operatorsParenthesisTest = provideSequenceOfSingletons(OperatorParenthesis::class)
    val operatorsAllTest: Sequence<Operator> = sequenceOf(operatorsBinaryTest).flatMap { it }

    //
    val buttonCategoryMappingBase = listOf(
        buttonsBinaryTest to ButtonCategory.Binary,
        buttonsControlsTest to ButtonCategory.Control,
        buttonsUnaryTest to ButtonCategory.Unary,
        buttonsParenthesisTest to ButtonCategory.Parenthesis,
        buttonsNumbersTest to ButtonCategory.Number,
    )

    val iButtonStyleMappingBase = listOf(
        buttonsBinaryTest to Pair(VividGamboge, White),
        buttonsUnaryTest to Pair(SilverGrey, Onyx),
        buttonsControlsTest to Pair(SilverGrey, Onyx),
        buttonsNumbersTest to Pair(Onyx,White),
        buttonsParenthesisTest to Pair(Onyx, White),
    )

    val iButtonStyleMappingOverrides = mapOf(
        ButtonCalculatorControl.Decimal to Pair(Onyx, White),
        ButtonCalculatorControl.Equals to Pair(VividGamboge, White),
    )

    val displayCategoryMappingBase = listOf(
        displaysInputsTest to DisplayCategory.Input,
    )

    val iDisplayStyleMappingBase = listOf(
        displaysInputsTest to Pair(Black, White),
    )

    @OptIn(ConceptClass::class)
    val iDisplayStyleMappingOverrides = mapOf(
        DisplayCalculatorInput.Scientific to Pair(Black, White),
    )
}