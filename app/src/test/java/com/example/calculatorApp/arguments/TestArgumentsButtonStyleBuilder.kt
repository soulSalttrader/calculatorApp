package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCategoryStyleBuilder
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.ButtonCalculatorList
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

data class TestArgumentsButtonStyleBuilder(
    val builder: ButtonCategoryStyleBuilder = ButtonCategoryStyleBuilder(),
    val arithmetics: Array<ButtonCalculatorArithmetic> = ButtonCalculatorList.arithmetics,
    val controls: Array<ButtonCalculatorControl> = ButtonCalculatorList.controls,
    val numbers: Array<ButtonCalculatorNumber> = ButtonCalculatorList.numbers,
) : TestArguments {

    fun provideControlStyle(): Stream<Arguments> {
        val baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx)
        val decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White)
        val plusMinusStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White)

        val styles = mapOf(
            ButtonCalculatorControl.Decimal to decimalStyle,
            ButtonCalculatorControl.PlusMinus to plusMinusStyle,
            *controls.map { it to baseStyle }.toTypedArray(),
        )

        return controls.map { button ->
            val builder = ButtonCategoryStyleBuilder()
            val style = builder.controlStyle(baseStyle, styles[button]).build()
            val expectedStyle = styles[button]

            Arguments.of(button, style, expectedStyle)
        }.stream()
    }

    fun provideArithmeticStyle(): Stream<Arguments> {
        val baseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White)

        val styles = mapOf(*arithmetics.map { it to baseStyle }.toTypedArray())

        return arithmetics.map { button ->
            val builder = ButtonCategoryStyleBuilder()
            val style = builder.arithmeticStyle(baseStyle).build()
            val expectedStyle = styles[button]

            Arguments.of(button, style, expectedStyle)
        }.stream()
    }

    fun provideNumberStyle(): Stream<Arguments> {
        val baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White)

        val styles = mapOf(*numbers.map { it to baseStyle }.toTypedArray())

        return numbers.map { button ->
            val builder = ButtonCategoryStyleBuilder()
            val style = builder.numberStyle(baseStyle).build()
            val expectedStyle = styles[button]

            Arguments.of(button, style, expectedStyle)
        }.stream()
    }

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