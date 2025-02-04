package com.example.calculatorApp.arguments

import androidx.compose.ui.graphics.Color
import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCategoryStyleBuilder
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
import org.junit.jupiter.params.provider.Arguments

data class TestArgumentsButtonStyleBuilder(
    val builder: ButtonCategoryStyleBuilder = ButtonCategoryStyleBuilder(),
    val arithmetics: Array<ButtonCalculatorArithmetic> = arrayOf(
        ButtonCalculatorArithmetic.Addition,
        ButtonCalculatorArithmetic.Subtraction,
        ButtonCalculatorArithmetic.Multiplication,
        ButtonCalculatorArithmetic.Division,
        ButtonCalculatorArithmetic.Equals,
    ),
    val controls: Array<ButtonCalculatorControl> = arrayOf(
        ButtonCalculatorControl.AllClear,
        ButtonCalculatorControl.Clear,
        ButtonCalculatorControl.PlusMinus,
        ButtonCalculatorControl.Percent,
        ButtonCalculatorControl.Decimal,
    ),
    val numbers: Array<ButtonCalculatorNumber> = arrayOf(
        ButtonCalculatorNumber.Zero,
        ButtonCalculatorNumber.One,
        ButtonCalculatorNumber.Two,
        ButtonCalculatorNumber.Three,
        ButtonCalculatorNumber.Four,
        ButtonCalculatorNumber.Five,
        ButtonCalculatorNumber.Six,
        ButtonCalculatorNumber.Seven,
        ButtonCalculatorNumber.Eight,
        ButtonCalculatorNumber.Nine,
    )
) : TestArguments {

    fun provideArithmeticStyle() = arithmetics.map { button ->
        val specificBackgroundColors = emptyMap<SymbolButton, Color>()
        val specificTextColors = emptyMap<SymbolButton, Color>()

        val elementStyle = when (button) {
            ButtonCalculatorArithmetic.Addition,
            ButtonCalculatorArithmetic.Subtraction,
            ButtonCalculatorArithmetic.Multiplication,
            ButtonCalculatorArithmetic.Division,
            ButtonCalculatorArithmetic.Equals -> ElementColorStyleImpl(VividGamboge, White)
        }

        val expectedBackgroundColor = specificBackgroundColors[button.symbol] ?: VividGamboge
        val expectedTextColor = specificTextColors[button.symbol] ?: White
        val style = builder.arithmeticStyle(elementStyle).build()

        Arguments.of(button, style, expectedBackgroundColor, expectedTextColor)
    }.stream()

    fun provideControlStyle() = controls.map { button ->
        val specificBackgroundColors = mapOf(SymbolButton.DECIMAL to Onyx)
        val specificTextColors = mapOf(SymbolButton.DECIMAL to White)

        val baseStyle = ElementColorStyleImpl(SilverGrey, Onyx)
        val decimalStyle = ElementColorStyleImpl(Onyx, White)

        val elementStyle = when (button) {
            ButtonCalculatorControl.AllClear,
            ButtonCalculatorControl.Clear,
            ButtonCalculatorControl.PlusMinus,
            ButtonCalculatorControl.Percent -> baseStyle
            ButtonCalculatorControl.Decimal -> decimalStyle
        }

        val expectedBackgroundColor = specificBackgroundColors[button.symbol] ?: SilverGrey
        val expectedTextColor = specificTextColors[button.symbol] ?: Onyx
        val style = builder.controlStyle(baseStyle, elementStyle).build()

        Arguments.of(button, style, expectedBackgroundColor, expectedTextColor)
    }.stream()

    fun provideNumberStyle() = numbers.map { button ->
        val elementStyle = when (button) {
            ButtonCalculatorNumber.Zero,
            ButtonCalculatorNumber.One,
            ButtonCalculatorNumber.Two,
            ButtonCalculatorNumber.Three,
            ButtonCalculatorNumber.Four,
            ButtonCalculatorNumber.Five,
            ButtonCalculatorNumber.Six,
            ButtonCalculatorNumber.Seven,
            ButtonCalculatorNumber.Eight,
            ButtonCalculatorNumber.Nine -> ElementColorStyleImpl(Onyx, White)
        }

        val backgroundColor = elementStyle.backgroundColor
        val textColor = elementStyle.textColor
        val style = builder.numberStyle(elementStyle).build()

        Arguments.of(button, style, backgroundColor, textColor)
    }.stream()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TestArgumentsButtonStyleBuilder

        if (builder != other.builder) return false
        if (!arithmetics.contentEquals(other.arithmetics)) return false
        if (!controls.contentEquals(other.controls)) return false
        if (!numbers.contentEquals(other.numbers)) return false

        return true
    }
    override fun hashCode(): Int {
        var result = builder.hashCode()
        result = 31 * result + arithmetics.contentHashCode()
        result = 31 * result + controls.contentHashCode()
        result = 31 * result + numbers.contentHashCode()
        return result
    }
}
