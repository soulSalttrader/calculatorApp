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
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsAllTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsBinaryTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsControlsTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsNumbersTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsParenthesisTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsUnaryTest
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream
import kotlin.streams.asStream

object TestArgumentsButtonStyleBuilder : TestArguments {

    fun provideButtonStyle(style: ElementCategoryStyleCollection<ElementColorStyle>? = null): Stream<Arguments> {
        val binaryBaseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, foregroundColor = White)
        val unaryBaseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, foregroundColor = Onyx)
        val controlsBaseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, foregroundColor = Onyx)
        val numbersBaseStyle = ElementColorStyleImpl(backgroundColor = Onyx, foregroundColor = White)
        val parenthesisBaseStyle = ElementColorStyleImpl(backgroundColor = Onyx, foregroundColor = White)

        val decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, foregroundColor = White)
        val equalsStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, foregroundColor = White)

        val testedStyle = style ?: StylesButton.iButtonStyle

        val expectedStyles = sequenceOf(
            buttonsBinaryTest.map { it to binaryBaseStyle },
            buttonsUnaryTest.map { it to unaryBaseStyle },
            buttonsControlsTest.map { it to controlsBaseStyle },
            buttonsNumbersTest.map { it to numbersBaseStyle },
            buttonsParenthesisTest.map { it to parenthesisBaseStyle },
        ).flatMap { it }
            .plus(
                sequenceOf(
                    // base style overridden for control buttons
                    ButtonCalculatorControl.Decimal to decimalStyle,
                    ButtonCalculatorControl.Equals to equalsStyle,
                )
            )
            .toMap()

        return buttonsAllTest.map { button ->
            val expectedStyle = expectedStyles[button]
            Arguments.of(button, testedStyle, expectedStyle)
        }.asStream()
    }
}