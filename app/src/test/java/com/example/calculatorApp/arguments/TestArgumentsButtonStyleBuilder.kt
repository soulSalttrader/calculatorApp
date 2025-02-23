package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementColorStyleImpl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.ui.theme.Onyx
import com.example.calculatorApp.ui.theme.SilverGrey
import com.example.calculatorApp.ui.theme.VividGamboge
import com.example.calculatorApp.ui.theme.White
import com.example.calculatorApp.utils.ButtonCalculatorList.allButtons
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorList.numbers
import com.example.calculatorApp.utils.ButtonCalculatorList.unary
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestArgumentsButtonStyleBuilder : TestArguments {

    fun provideButtonStyle(style: ElementCategoryStyleCollection<ElementColorStyle>? = null): Stream<Arguments> {
        val controlsBaseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx)
        val numbersBaseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White)
        val arithmeticsBaseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White)
        val decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White)
        val equalsStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White)

        val testedStyle = style ?: StylesButton.iButtonStyle

        val expectedStyles = mapOf(
            *binary.map { it to arithmeticsBaseStyle }.toTypedArray(),
            *unary.map { it to controlsBaseStyle }.toTypedArray(),
            *controls.map { it to controlsBaseStyle }.toTypedArray(),
            *numbers.map { it to numbersBaseStyle }.toTypedArray(),

            // base style overridden for control buttons
            ButtonCalculatorControl.Decimal to decimalStyle,
            ButtonCalculatorControl.Equals to equalsStyle,
        )

        return allButtons.map { button ->
            val expectedStyle = expectedStyles[button]
            Arguments.of(button, testedStyle, expectedStyle)
        }.stream()
    }
}