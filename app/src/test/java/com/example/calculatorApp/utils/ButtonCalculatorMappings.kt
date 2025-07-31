package com.example.calculatorApp.utils

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White

object ButtonCalculatorMappings {

    val binaryVisualsMap: Map<ButtonCalculatorBinary, VisualsButton> = mapOf(
        ButtonCalculatorBinary.Addition to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.ADDITION,
        ),
        ButtonCalculatorBinary.Subtraction to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.SUBTRACTION,
        ),
        ButtonCalculatorBinary.Multiplication to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.MULTIPLICATION,
        ),
        ButtonCalculatorBinary.Division to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.DIVISION,
        ),
    )

    val unaryColorMap: Map<ButtonCalculatorUnary, Color> = mapOf(
        ButtonCalculatorUnary.Sign to SilverGrey,
        ButtonCalculatorUnary.Percentage to SilverGrey,
    )

    val unarySymbolMap: Map<ButtonCalculatorUnary, SymbolButton> = mapOf(
        ButtonCalculatorUnary.Sign to SymbolButton.SIGN,
        ButtonCalculatorUnary.Percentage to SymbolButton.PERCENTAGE,
    )

    val unaryIsPrefix: Map<ButtonCalculatorUnary, Boolean> = mapOf(
        ButtonCalculatorUnary.Sign to true,
        ButtonCalculatorUnary.Percentage to false,
    )

    val unaryIsSuffix: Map<ButtonCalculatorUnary, Boolean> = mapOf(
        ButtonCalculatorUnary.Sign to false,
        ButtonCalculatorUnary.Percentage to true,
    )

    val controlVisualsMap: Map<ButtonCalculatorControl, VisualsButton> = mapOf(
        ButtonCalculatorControl.AllClear to VisualsButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.ALL_CLEAR,
        ),
        ButtonCalculatorControl.Clear to VisualsButton(
            background = SilverGrey,
            foreground = Onyx,
            symbol = SymbolButton.CLEAR,
        ),
        ButtonCalculatorControl.Decimal to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.DECIMAL,
        ),
        ButtonCalculatorControl.Equals to VisualsButton(
            background = VividGamboge,
            foreground = White,
            symbol = SymbolButton.EQUALS,
        ),
    )

    val numberVisualsMap: Map<ButtonCalculatorNumber, VisualsButton> = mapOf(
        ButtonCalculatorNumber.Zero to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ZERO,
        ),
        ButtonCalculatorNumber.One to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.ONE,
        ),
        ButtonCalculatorNumber.Two to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.TWO,
        ),
        ButtonCalculatorNumber.Three to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.THREE,
        ),
        ButtonCalculatorNumber.Four to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FOUR,
        ),
        ButtonCalculatorNumber.Five to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.FIVE,
        ),
        ButtonCalculatorNumber.Six to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SIX,
        ),
        ButtonCalculatorNumber.Seven to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.SEVEN,
        ),
        ButtonCalculatorNumber.Eight to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.EIGHT,
        ),
        ButtonCalculatorNumber.Nine to VisualsButton(
            background = Onyx,
            foreground = White,
            symbol = SymbolButton.NINE,
        ),
    )

    val parenthesisColorMap: Map<ButtonCalculatorParenthesis, Color> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis to Onyx,
        ButtonCalculatorParenthesis.CloseParenthesis to Onyx,
    )

    val parenthesisSymbolMap: Map<ButtonCalculatorParenthesis, SymbolButton> = mapOf(
        ButtonCalculatorParenthesis.OpenParenthesis to SymbolButton.OPEN_PARENT,
        ButtonCalculatorParenthesis.CloseParenthesis to SymbolButton.CLOSE_PARENT,
    )

    val buttonButtonMap: Map<Button, Button> = mapOf(
        ButtonCalculatorBinary.Addition to ButtonCalculatorBinary.Addition,
        ButtonCalculatorBinary.Subtraction to ButtonCalculatorBinary.Subtraction,
        ButtonCalculatorBinary.Multiplication to ButtonCalculatorBinary.Multiplication,
        ButtonCalculatorBinary.Division to ButtonCalculatorBinary.Division,

        ButtonCalculatorUnary.Sign to ButtonCalculatorUnary.Sign,
        ButtonCalculatorUnary.Percentage to ButtonCalculatorUnary.Percentage,

        ButtonCalculatorControl.AllClear to ButtonCalculatorControl.AllClear,
        ButtonCalculatorControl.Clear to ButtonCalculatorControl.Clear,
        ButtonCalculatorControl.Decimal to ButtonCalculatorControl.Decimal,
        ButtonCalculatorControl.Equals to ButtonCalculatorControl.Equals,

        ButtonCalculatorNumber.Zero to ButtonCalculatorNumber.Zero,
        ButtonCalculatorNumber.One to ButtonCalculatorNumber.One,
        ButtonCalculatorNumber.Two to ButtonCalculatorNumber.Two,
        ButtonCalculatorNumber.Three to ButtonCalculatorNumber.Three,
        ButtonCalculatorNumber.Four to ButtonCalculatorNumber.Four,
        ButtonCalculatorNumber.Five to ButtonCalculatorNumber.Five,
        ButtonCalculatorNumber.Six to ButtonCalculatorNumber.Six,
        ButtonCalculatorNumber.Seven to ButtonCalculatorNumber.Seven,
        ButtonCalculatorNumber.Eight to ButtonCalculatorNumber.Eight,
        ButtonCalculatorNumber.Nine to ButtonCalculatorNumber.Nine,

        ButtonCalculatorParenthesis.CloseParenthesis to ButtonCalculatorParenthesis.CloseParenthesis,
        ButtonCalculatorParenthesis.OpenParenthesis to ButtonCalculatorParenthesis.OpenParenthesis,
    )
}
