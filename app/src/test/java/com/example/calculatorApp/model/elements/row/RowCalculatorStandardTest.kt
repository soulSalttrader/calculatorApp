package com.example.calculatorApp.model.elements.row

import androidx.compose.ui.graphics.toArgb
import com.example.calculatorApp.arguments.TestArgumentsRow
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.styles.StylesRow
import com.example.calculatorApp.utils.ColorToLongConverter
import com.example.calculatorApp.utils.RowCalculatorList
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class RowCalculatorStandardTest {

    @Nested
    inner class GetCategory {

        // Arrange: Setup test data (row instance)
        private fun provideArguments(): Stream<Row> {
            return RowCalculatorList.standards.asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each standard row`(row: RowCalculatorStandard) {
            // Act & Assert: Check if the row's category matches the expected category
            RowCategory.Standard shouldBe row.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(row: RowCalculatorStandard) {
            // Arrange: Create an empty style collection (default is empty)
            val emptyStyleCollection = ElementCategoryStyleCollectionImpl<ElementColorStyle>()

            // Act & Assert: Call getBackgroundColor, which internally calls getStyle and should throw an exception
            row.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    row.getBackgroundColor(emptyStyleCollection)
                }

                // Assert: Validate the exception message
                exception.message shouldBe "Category '${row.getCategory()}' not found."
            }
        }
    }

    @Nested
    inner class GetBackgroundColor {

        // Arrange: Setup test data (row instance)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsRow.provideRowColors().asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each standard row`(
            row: RowCalculatorStandard,
            @ConvertWith(ColorToLongConverter::class) expectedColor: Long,
        ) {
            // Arrange:
            val style = StylesRow.iRowStyle

            // Act:
            val actualColor = row.getBackgroundColor(style).toArgb()

            // Assert:
            actualColor shouldBe expectedColor
        }
    }

    @Nested
    inner class GetTextColor {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsRow.provideRowColors().asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right textColor for each standard row`(
            row: RowCalculatorStandard,
            @ConvertWith(ColorToLongConverter::class) expectedColor: Long,
        ) {
            // Arrange:
            val style = StylesRow.iRowStyle

            // Act:
            val actualColor = row.getBackgroundColor(style).toArgb()

            // Assert:
            actualColor shouldBe expectedColor
        }
    }

    @Nested
    inner class GetButtons {

        // Arrange: Setup test data (button instance and expected symbol)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsRow.provideStandardButtons().asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map buttons to rows`(
            row: RowCalculatorStandard,
            expectedButtons: Sequence<ButtonData>,
        ) {
            // Act & Assert:
            expectedButtons.shouldBeEqualToIgnoringFields(row.buttons, ButtonData::layout)
        }
    }
}