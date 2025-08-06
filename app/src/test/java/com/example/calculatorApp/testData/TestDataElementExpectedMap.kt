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
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.Visuals
import com.example.calculatorApp.utils.VisualsButton
import com.example.calculatorApp.utils.VisualsButtonUnary
import com.example.calculatorApp.utils.VisualsDisplay
import com.example.calculatorApp.utils.VisualsRow
import kotlin.reflect.KClass

object TestDataElementExpectedMap {

    val binaryVisualsMapTest: Map<KClass<out Button>, Visuals> = mapOf(
        ButtonCalculatorBinary.Addition::class to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.ADDITION,
        ),
        ButtonCalculatorBinary.Subtraction::class to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.SUBTRACTION,
        ),
        ButtonCalculatorBinary.Multiplication::class to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.MULTIPLICATION,
        ),
        ButtonCalculatorBinary.Division::class to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.DIVISION,
        ),
    )

    val unaryVisualsMapTest: Map<KClass<out Button>, Visuals> = mapOf(
        ButtonCalculatorUnary.Sign::class to VisualsButtonUnary(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.SIGN,
            isPrefix = true,
        ),
        ButtonCalculatorUnary.Percentage::class to VisualsButtonUnary(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.PERCENTAGE,
            isPrefix = false,
        ),
    )

    val controlVisualsMapTest: Map<KClass<out Button>, Visuals> = mapOf(
        ButtonCalculatorControl.AllClear::class to VisualsButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.ALL_CLEAR,
        ),
        ButtonCalculatorControl.Clear::class to VisualsButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.CLEAR,
        ),
        ButtonCalculatorControl.Decimal::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.DECIMAL,
        ),
        ButtonCalculatorControl.Equals::class to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.EQUALS,
        ),
    )

    val numberVisualsMapTest: Map<KClass<out Button>, Visuals> = mapOf(
        ButtonCalculatorNumber.Zero::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ZERO,
        ),
        ButtonCalculatorNumber.One::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ONE,
        ),
        ButtonCalculatorNumber.Two::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.TWO,
        ),
        ButtonCalculatorNumber.Three::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.THREE,
        ),
        ButtonCalculatorNumber.Four::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FOUR,
        ),
        ButtonCalculatorNumber.Five::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FIVE,
        ),
        ButtonCalculatorNumber.Six::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SIX,
        ),
        ButtonCalculatorNumber.Seven::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SEVEN,
        ),
        ButtonCalculatorNumber.Eight::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.EIGHT,
        ),
        ButtonCalculatorNumber.Nine::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.NINE,
        ),
    )

    val parenthesisVisualsMap: Map<KClass<out Button>, Visuals> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.OPEN_PARENT,
        ),
        ButtonCalculatorParenthesis.CloseParenthesis::class to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.CLOSE_PARENT,
        ),
    )

    @OptIn(ConceptClass::class)
    val inputsVisualsMapTest: Map<KClass<out Display>, Visuals> = mapOf(
        DisplayCalculatorInput.Standard::class to VisualsDisplay(
            background = Black,
            foreground = White,
        ),
        DisplayCalculatorInput.Scientific::class to VisualsDisplay(
            background = Black,
            foreground = White,
        ),
    )

    val standardVisualsMapTest: Map<KClass<out Row>, Visuals> = mapOf(
        RowCalculatorStandard.Standard1::class to VisualsRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataBinaryTest,
        ),
        RowCalculatorStandard.Standard2::class to VisualsRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataNumbersTest,
        ),
        RowCalculatorStandard.Standard3::class to VisualsRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataBinaryTest,
        ),
        RowCalculatorStandard.Standard4::class to VisualsRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataNumbersTest,
        ),
        RowCalculatorStandard.Standard5::class to VisualsRow(
            background = Black,
            foreground = White,
            buttonData = TestDataElementSeq.buttonDataControlsTest,
        ),
    )
}