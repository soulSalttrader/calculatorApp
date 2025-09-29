package com.example.calculatorApp.testData

import androidx.compose.ui.graphics.Color
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
import com.example.calculatorApp.model.elements.display.Display
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayCategory
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.model.elements.row.RowCategory
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.TestUtils.provideSequenceOfSingletons
import kotlin.reflect.KClass

object TestDataElement {

    // Buttons
    val buttonsBinaryTest = provideSequenceOfSingletons(ButtonCalculatorBinary::class)
    val buttonsBinaryClassTest = buttonsBinaryTest.map { it::class }
    val buttonsUnaryTest = provideSequenceOfSingletons(ButtonCalculatorUnary::class)
    val buttonsUnaryClassTest = buttonsUnaryTest.map { it::class }
    val buttonsControlsTest = provideSequenceOfSingletons(ButtonCalculatorControl::class)
    val buttonsControlsClassTest = buttonsControlsTest.map { it::class }
    val buttonsNumbersTest = provideSequenceOfSingletons(ButtonCalculatorNumber::class)
    val buttonsNumbersClassTest = buttonsNumbersTest.map { it::class }
    val buttonsParenthesisTest = provideSequenceOfSingletons(ButtonCalculatorParenthesis::class)
    val buttonsParenthesisClassTest = buttonsParenthesisTest.map { it::class }

    val buttonsAllTest: Sequence<Button> = sequenceOf(
        buttonsBinaryTest,
        buttonsUnaryTest,
        buttonsControlsTest,
        buttonsNumbersTest,
        buttonsParenthesisTest,
    ).flatMap { it }

    // Displays
    val displaysInputsTest = provideSequenceOfSingletons(DisplayCalculatorInput::class)
    val displaysInputsClassTest = displaysInputsTest.map { it::class }

    // Rows
    val buttonDataBinaryTest = buttonsBinaryTest.map { ButtonData(it) }
    val buttonDataControlsTest = buttonsControlsTest.map { ButtonData(it) }
    val buttonDataNumbersTest = buttonsNumbersTest.map { ButtonData(it) }

    val rowsStandardClassTest = sequenceOf(
        RowCalculatorStandard.Standard1::class,
        RowCalculatorStandard.Standard2::class,
        RowCalculatorStandard.Standard3::class,
        RowCalculatorStandard.Standard4::class,
        RowCalculatorStandard.Standard5::class,
    )

    val rowsStandardTest = sequenceOf(
        RowCalculatorStandard.Standard1(buttonDataBinaryTest),
        RowCalculatorStandard.Standard2(buttonDataControlsTest),
        RowCalculatorStandard.Standard3(buttonDataNumbersTest),
        RowCalculatorStandard.Standard4(buttonDataBinaryTest),
        RowCalculatorStandard.Standard5(buttonDataNumbersTest),
    )

    // Operators
    val operatorsBinaryTest = provideSequenceOfSingletons(OperatorBinary::class)
    val operatorsUnaryPrefixTest = provideSequenceOfSingletons(OperatorUnary.Prefix::class)
    val operatorsUnarySuffixTest = provideSequenceOfSingletons(OperatorUnary.Suffix::class)
    val operatorsParenthesisTest = provideSequenceOfSingletons(OperatorParenthesis::class)
    val operatorsAllTest: Sequence<Operator> = sequenceOf(
        operatorsBinaryTest,
                operatorsUnaryPrefixTest,
                operatorsUnarySuffixTest,
                operatorsParenthesisTest,
    ).flatMap { it }

    //
    val buttonCategoryMappingBase = listOf(
        buttonsBinaryClassTest to ButtonCategory.Binary,
        buttonsControlsClassTest to ButtonCategory.Control,
        buttonsUnaryClassTest to ButtonCategory.Unary,
        buttonsParenthesisClassTest to ButtonCategory.Parenthesis,
        buttonsNumbersClassTest to ButtonCategory.Number,
    )

    val iButtonStyleMappingBase = listOf(
        buttonsBinaryClassTest to Pair(VividGamboge, White),
        buttonsUnaryClassTest to Pair(SilverGrey, Onyx),
        buttonsControlsClassTest to Pair(SilverGrey, Onyx),
        buttonsNumbersClassTest to Pair(Onyx,White),
        buttonsParenthesisClassTest to Pair(Onyx, White),
    )

    val iButtonStyleMappingOverrides: Map<KClass<out Button>, Pair<Color, Color>> = mapOf(
        ButtonCalculatorControl.Decimal::class to Pair(Onyx, White),
        ButtonCalculatorControl.Equals::class to Pair(VividGamboge, White),
    )

    val displayCategoryMappingBase = listOf(
        displaysInputsClassTest to DisplayCategory.Input,
    )

    val iDisplayStyleMappingBase = listOf(
        displaysInputsClassTest to Pair(Black, White),
    )

    @OptIn(ConceptClass::class)
    val iDisplayStyleMappingOverrides: Map<KClass<out Display>, Pair<Color, Color>> = mapOf(
        DisplayCalculatorInput.Scientific::class to Pair(Black, White),
    )

    val rowCategoryMappingBase = listOf(
        rowsStandardClassTest to RowCategory.Standard,
    )

    val iRowStyleMappingBase = listOf(
        rowsStandardClassTest to Pair(Black, White),
    )
}