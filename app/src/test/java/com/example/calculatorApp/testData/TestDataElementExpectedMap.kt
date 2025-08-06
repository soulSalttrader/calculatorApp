package com.example.calculatorApp.testData

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.display.Display
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.row.Row
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.testData.expected.ExpectedButton
import com.example.calculatorApp.testData.expected.ExpectedButtonUnary
import com.example.calculatorApp.testData.expected.ExpectedDisplay
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.expected.ExpectedRow
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
import kotlin.reflect.KClass

object TestDataElementExpectedMap {

    val binaryExpectedMapTest: Map<KClass<out Button>, ExpectedElement> = mapOf(
        ButtonCalculatorBinary.Addition::class to ExpectedButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.ADDITION,
        ),
        ButtonCalculatorBinary.Subtraction::class to ExpectedButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.SUBTRACTION,
        ),
        ButtonCalculatorBinary.Multiplication::class to ExpectedButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.MULTIPLICATION,
        ),
        ButtonCalculatorBinary.Division::class to ExpectedButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.DIVISION,
        ),
    )

    val unaryExpectedMapTest: Map<KClass<out Button>, ExpectedElement> = mapOf(
        ButtonCalculatorUnary.Sign::class to ExpectedButtonUnary(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.SIGN,
            isPrefix = true,
        ),
        ButtonCalculatorUnary.Percentage::class to ExpectedButtonUnary(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.PERCENTAGE,
            isPrefix = false,
        ),
    )

    val controlExpectedMapTest: Map<KClass<out Button>, ExpectedElement> = mapOf(
        ButtonCalculatorControl.AllClear::class to ExpectedButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.ALL_CLEAR,
        ),
        ButtonCalculatorControl.Clear::class to ExpectedButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.CLEAR,
        ),
        ButtonCalculatorControl.Decimal::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.DECIMAL,
        ),
        ButtonCalculatorControl.Equals::class to ExpectedButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.EQUALS,
        ),
    )

    val numberExpectedMapTest: Map<KClass<out Button>, ExpectedElement> = mapOf(
        ButtonCalculatorNumber.Zero::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ZERO,
        ),
        ButtonCalculatorNumber.One::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ONE,
        ),
        ButtonCalculatorNumber.Two::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.TWO,
        ),
        ButtonCalculatorNumber.Three::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.THREE,
        ),
        ButtonCalculatorNumber.Four::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FOUR,
        ),
        ButtonCalculatorNumber.Five::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FIVE,
        ),
        ButtonCalculatorNumber.Six::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SIX,
        ),
        ButtonCalculatorNumber.Seven::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SEVEN,
        ),
        ButtonCalculatorNumber.Eight::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.EIGHT,
        ),
        ButtonCalculatorNumber.Nine::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.NINE,
        ),
    )

    val parenthesisExpectedMap: Map<KClass<out Button>, ExpectedElement> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.OPEN_PARENT,
        ),
        ButtonCalculatorParenthesis.CloseParenthesis::class to ExpectedButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.CLOSE_PARENT,
        ),
    )

    @OptIn(ConceptClass::class)
    val inputsExpectedMapTest: Map<KClass<out Display>, ExpectedElement> = mapOf(
        DisplayCalculatorInput.Standard::class to ExpectedDisplay(
            background = Black,
            foreground = White,
        ),
        DisplayCalculatorInput.Scientific::class to ExpectedDisplay(
            background = Black,
            foreground = White,
        ),
    )

    val standardExpectedMapTest: Map<KClass<out Row>, ExpectedElement> = mapOf(
        RowCalculatorStandard.Standard1::class to ExpectedRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataBinaryTest,
        ),
        RowCalculatorStandard.Standard2::class to ExpectedRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataNumbersTest,
        ),
        RowCalculatorStandard.Standard3::class to ExpectedRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataBinaryTest,
        ),
        RowCalculatorStandard.Standard4::class to ExpectedRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataNumbersTest,
        ),
        RowCalculatorStandard.Standard5::class to ExpectedRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataControlsTest,
        ),
    )
}