package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.toArgb
import com.example.calculatorApp.ColorToLongConverter
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.ui.theme.VividGamboge
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ButtonCalculatorArithmeticTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class GetCategory {

        // Arrange: Setup test data (button instance)
        private fun buttonCalculatorArithmeticButtons(): Stream<ButtonCalculatorArithmetic> = Stream.of(
            ButtonCalculatorArithmetic.Addition,
            ButtonCalculatorArithmetic.Subtraction,
            ButtonCalculatorArithmetic.Multiplication,
            ButtonCalculatorArithmetic.Division,
            ButtonCalculatorArithmetic.Equals
        )

        @ParameterizedTest
        @MethodSource("buttonCalculatorArithmeticButtons")
        fun `should get the right category for each arithmetic button`(button: ButtonCalculatorArithmetic) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Arithmetic shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("buttonCalculatorArithmeticButtons")
        fun `should throw exception when category is not found`(button: ButtonCalculatorArithmetic) {
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

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class GetBackgroundColor {

        private fun provideArithmeticButtonColorMappings() = Stream.of(
            Arguments.of(ButtonCalculatorArithmetic.Addition, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Subtraction, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Multiplication, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Division, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Equals, VividGamboge)
        )

        @ParameterizedTest
        @MethodSource("provideArithmeticButtonColorMappings")
        fun `should get the right backgroundColor for each arithmetic button`(
            button: ButtonCalculatorArithmetic,
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

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class GetTextColor {

        private fun provideArithmeticButtonColorMappings() = Stream.of(
            Arguments.of(ButtonCalculatorArithmetic.Addition, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Subtraction, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Multiplication, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Division, VividGamboge),
            Arguments.of(ButtonCalculatorArithmetic.Equals, VividGamboge)
        )

        @ParameterizedTest
        @MethodSource("provideArithmeticButtonColorMappings")
        fun `should get the right textColor for each arithmetic button`(
            button: ButtonCalculatorArithmetic,
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

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class GetSymbol {

        // Arrange: Setup test data (button instance and expected symbol)
        private fun provideButtonSymbolMappings(): Stream<Arguments> = Stream.of(
            Arguments.of(ButtonCalculatorArithmetic.Addition, SymbolButton.ADDITION),
            Arguments.of(ButtonCalculatorArithmetic.Subtraction, SymbolButton.SUBTRACTION),
            Arguments.of(ButtonCalculatorArithmetic.Multiplication, SymbolButton.MULTIPLICATION),
            Arguments.of(ButtonCalculatorArithmetic.Division, SymbolButton.DIVISION),
            Arguments.of(ButtonCalculatorArithmetic.Equals, SymbolButton.EQUALS)
        )

        @ParameterizedTest
        @MethodSource("provideButtonSymbolMappings")
        fun `should correctly map symbols to buttons`(
            button: ButtonCalculatorArithmetic,
            expectedSymbol: SymbolButton,
        ) {
            // Act & Assert: Check if the button's symbol matches the expected symbol
            expectedSymbol shouldBe button.symbol
        }
    }
}