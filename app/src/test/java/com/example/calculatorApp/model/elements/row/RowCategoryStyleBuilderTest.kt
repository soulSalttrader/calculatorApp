package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.arguments.TestArgumentsRowStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import io.kotest.assertions.withClue
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RowCategoryStyleBuilderTest {

    @Nested
    inner class StandardStyle {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsRowStyleBuilder.provideRowStyle().filter { it.get()[0] is RowCalculatorStandard }
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply standard style correctly`(
            row: RowCalculatorStandard,
            styles: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = row.getBackgroundColor(styles)
            val actualForegroundColor = row.getForegroundColor(styles)

            // Assert:
            withClue("Background color mismatch for ${row::class.simpleName}.") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Foreground color mismatch for ${row::class.simpleName}.") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }

    @Nested
    inner class Build {
        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsRowStyleBuilder.provideRowStyle()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should build and return the expected style collection`(
            row: Row,
            style: ElementCategoryStyleCollection<ElementColorStyle>,
            expectedStyle: ElementColorStyle,
        ) {
            // Act:
            val actualBackgroundColor = row.getBackgroundColor(style)
            val actualForegroundColor = row.getForegroundColor(style)
            // Assert:
            withClue("Background color mismatch for ${row::class.simpleName}") {
                expectedStyle.backgroundColor shouldBe actualBackgroundColor
            }

            withClue("Foreground color mismatch for ${row::class.simpleName}") {
                expectedStyle.foregroundColor shouldBe actualForegroundColor
            }
        }
    }
}