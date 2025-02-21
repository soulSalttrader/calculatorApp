package com.example.calculatorApp.utils

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge

object ButtonCalculatorMappings {

    // Map for ButtonCalculatorArithmetic to Color (future-proofing)
    val arithmeticColorMap: Map<ButtonCalculatorArithmetic, Any> = mapOf(
        ButtonCalculatorArithmetic.Addition to VividGamboge,
        ButtonCalculatorArithmetic.Subtraction to VividGamboge,
        ButtonCalculatorArithmetic.Multiplication to VividGamboge,
        ButtonCalculatorArithmetic.Division to VividGamboge,
        ButtonCalculatorArithmetic.Equals to VividGamboge
    )

    // Map for ButtonCalculatorArithmetic to SymbolButton
    val arithmeticSymbolMap: Map<ButtonCalculatorArithmetic, SymbolButton> = mapOf(
        ButtonCalculatorArithmetic.Addition to SymbolButton.ADDITION,
        ButtonCalculatorArithmetic.Subtraction to SymbolButton.SUBTRACTION,
        ButtonCalculatorArithmetic.Multiplication to SymbolButton.MULTIPLICATION,
        ButtonCalculatorArithmetic.Division to SymbolButton.DIVISION,
        ButtonCalculatorArithmetic.Equals to SymbolButton.EQUALS
    )

    // Map for ButtonCalculatorControl to color and symbol
    val controlColorMap: Map<ButtonCalculatorControl, Any> = mapOf(
        ButtonCalculatorControl.Decimal to Onyx,
        ButtonCalculatorControl.AllClear to SilverGrey,
        ButtonCalculatorControl.Clear to SilverGrey,
        ButtonCalculatorControl.Sign to SilverGrey,
        ButtonCalculatorControl.Percentage to SilverGrey
    )

    val controlSymbolMap: Map<ButtonCalculatorControl, SymbolButton> = mapOf(
        ButtonCalculatorControl.AllClear to SymbolButton.ALL_CLEAR,
        ButtonCalculatorControl.Clear to SymbolButton.CLEAR,
        ButtonCalculatorControl.Sign to SymbolButton.PLUS_MINUS,
        ButtonCalculatorControl.Percentage to SymbolButton.PERCENT,
        ButtonCalculatorControl.Decimal to SymbolButton.DECIMAL
    )

    // Map for ButtonCalculatorNumber to Color (future-proofing)
    val numberColorMap: Map<ButtonCalculatorNumber, Any> = mapOf(
        ButtonCalculatorNumber.Zero to Onyx,
        ButtonCalculatorNumber.One to Onyx,
        ButtonCalculatorNumber.Two to Onyx,
        ButtonCalculatorNumber.Three to Onyx,
        ButtonCalculatorNumber.Four to Onyx,
        ButtonCalculatorNumber.Five to Onyx,
        ButtonCalculatorNumber.Six to Onyx,
        ButtonCalculatorNumber.Seven to Onyx,
        ButtonCalculatorNumber.Eight to Onyx,
        ButtonCalculatorNumber.Nine to Onyx
    )

    // Map for ButtonCalculatorNumber to SymbolButton
    val numberSymbolMap: Map<ButtonCalculatorNumber, SymbolButton> = mapOf(
        ButtonCalculatorNumber.Zero to SymbolButton.ZERO,
        ButtonCalculatorNumber.One to SymbolButton.ONE,
        ButtonCalculatorNumber.Two to SymbolButton.TWO,
        ButtonCalculatorNumber.Three to SymbolButton.THREE,
        ButtonCalculatorNumber.Four to SymbolButton.FOUR,
        ButtonCalculatorNumber.Five to SymbolButton.FIVE,
        ButtonCalculatorNumber.Six to SymbolButton.SIX,
        ButtonCalculatorNumber.Seven to SymbolButton.SEVEN,
        ButtonCalculatorNumber.Eight to SymbolButton.EIGHT,
        ButtonCalculatorNumber.Nine to SymbolButton.NINE
    )

    val buttonButtonMap: Map<Button, Button> = mapOf(
        ButtonCalculatorArithmetic.Addition to ButtonCalculatorArithmetic.Addition,
        ButtonCalculatorArithmetic.Subtraction to ButtonCalculatorArithmetic.Subtraction,
        ButtonCalculatorArithmetic.Multiplication to ButtonCalculatorArithmetic.Multiplication,
        ButtonCalculatorArithmetic.Division to ButtonCalculatorArithmetic.Division,
        ButtonCalculatorArithmetic.Equals to ButtonCalculatorArithmetic.Equals,

        ButtonCalculatorControl.AllClear to ButtonCalculatorControl.AllClear,
        ButtonCalculatorControl.Clear to ButtonCalculatorControl.Clear,
        ButtonCalculatorControl.Sign to ButtonCalculatorControl.Sign,
        ButtonCalculatorControl.Percentage to ButtonCalculatorControl.Percentage,
        ButtonCalculatorControl.Decimal to ButtonCalculatorControl.Decimal,

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
    )
}
