package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsButtonStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ButtonCategoryStyleBuilderTest {

    @Nested
    inner class BinaryStyle {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder.provideButtonStyle().filter { it.get()[0] is ButtonCalculatorBinary }
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply arithmetic style correctly`(
            button: ButtonCalculatorBinary,
            styles: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = button.getBackgroundColor(styles)
            val actualForegroundColor = button.getForegroundColor(styles)

            // Assert:
            withClue("Background color mismatch for ${button.symbol.label}.") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Text color mismatch for ${button.symbol.label}.") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }

    @Nested
    inner class ControlStyle {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder.provideButtonStyle().filter { it.get()[0] is ButtonCalculatorControl }
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply control style correctly`(
            button: ButtonCalculatorControl,
            styles: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = button.getBackgroundColor(styles)
            val actualForegroundColor = button.getForegroundColor(styles)

            // Assert:
            withClue("Background color mismatch for ${button.symbol.label}.") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Text color mismatch for ${button.symbol.label}.") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply control style correctly with default and decimal style`(
            button: ButtonCalculatorControl,
            styles: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = button.getBackgroundColor(styles)
            val actualForegroundColor = button.getForegroundColor(styles)

            // Assert:
            withClue("Background color mismatch for ${button.symbol.label}.") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Text color mismatch for ${button.symbol.label}.") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }

    @Nested
    inner class NumberStyle {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder.provideButtonStyle().filter { it.get()[0] is ButtonCalculatorNumber }
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply number style correctly`(
            button: ButtonCalculatorNumber,
            styles: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = button.getBackgroundColor(styles)
            val actualForegroundColor = button.getForegroundColor(styles)

            // Assert:
            withClue("Background color mismatch for ${button.symbol.label}") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Text color mismatch for ${button.symbol.label}") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }

    @Nested
    inner class Build {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder.provideButtonStyle()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should build and return the expected style collection`(
            button: Button,
            style: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = button.getBackgroundColor(style)
            val actualForegroundColor = button.getForegroundColor(style)
            // Assert:
            withClue("Background color mismatch for ${button.symbol.label}") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Text color mismatch for ${button.symbol.label}") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }
}