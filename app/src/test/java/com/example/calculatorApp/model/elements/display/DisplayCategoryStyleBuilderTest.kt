package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.arguments.TestArgumentsDisplayStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class DisplayCategoryStyleBuilderTest {

    @Nested
    inner class InputStyle {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsDisplayStyleBuilder.provideDisplayStyle().filter { it.get()[0] is DisplayCalculatorInput }.asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply input style correctly`(
            display: DisplayCalculatorInput,
            styles: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = display.getBackgroundColor(styles)
            val actualForegroundColor = display.getForegroundColor(styles)

            // Assert:
            withClue("Background color mismatch for ${display::class.simpleName}.") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Foreground color mismatch for ${display::class.simpleName}.") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }

    @Nested
    inner class Build {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsDisplayStyleBuilder.provideDisplayStyle().asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should build and return the expected style collection`(
            display: DisplayCalculatorInput,
            style: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = display.getBackgroundColor(style)
            val actualForegroundColor = display.getForegroundColor(style)
            // Assert:
            withClue("Background color mismatch for ${display::class.simpleName}") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Foreground color mismatch for ${display::class.simpleName}") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }
}