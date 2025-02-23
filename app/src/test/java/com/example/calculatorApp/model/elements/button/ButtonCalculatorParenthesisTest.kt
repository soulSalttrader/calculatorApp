package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.toArgb
import com.example.calculatorApp.arguments.TestArgumentsButton
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.ButtonCalculatorList
import com.example.calculatorApp.utils.ColorToLongConverter
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ButtonCalculatorParenthesisTest {

    @Nested
    inner class GetCategory {

        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Array<ButtonCalculatorParenthesis> {
            return ButtonCalculatorList.parenthesis
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each parenthesis button`(button: ButtonCalculatorParenthesis) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Parenthesis shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(button: ButtonCalculatorParenthesis) {
            // Arrange: Create an empty style collection (default is empty)
            val emptyStyleCollection = ElementCategoryStyleCollectionImpl<ElementColorStyle>()

            // Act & Assert: Call getBackgroundColor, which internally calls getStyle and should throw an exception
            button.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    button.getBackgroundColor(emptyStyleCollection)
                }

                // Assert: Validate the exception message
                exception.message shouldBe "Category '${button.getCategory()}' not found."
            }
        }
    }

    @Nested
    inner class GetBackgroundColor {

        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButton.provideParenthesisColors()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each parenthesis button`(
            button: ButtonCalculatorParenthesis,
            @ConvertWith(ColorToLongConverter::class) expectedColor: Long,
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle

            // Act:
            val actualColor = button.getBackgroundColor(style).toArgb()

            // Assert:
            actualColor shouldBe expectedColor
        }
    }

    @Nested
    inner class GetTextColor {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButton.provideParenthesisColors()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right textColor for each parenthesis button`(
            button: ButtonCalculatorParenthesis,
            @ConvertWith(ColorToLongConverter::class) expectedColor: Long,
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle

            // Act:
            val actualColor = button.getBackgroundColor(style).toArgb()

            // Assert:
            actualColor shouldBe expectedColor
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange: Setup test data (button instance and expected symbol)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButton.provideParenthesisSymbols()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            button: ButtonCalculatorParenthesis,
            expectedSymbol: SymbolButton,
        ) {
            // Act & Assert: Check if the button's symbol matches the expected symbol
            expectedSymbol shouldBe button.symbol
        }
    }
}