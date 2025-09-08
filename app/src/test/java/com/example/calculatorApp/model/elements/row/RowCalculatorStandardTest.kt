package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideRowTestCases
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.styles.StylesRow
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElement.rowsStandardTest
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.equality.shouldBeEqualToIgnoringFields
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class RowCalculatorStandardTest {

    @Nested
    inner class GetCategory {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideRowTestCases(rowsStandardTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each standard row`(
            testData: TestCase<InputElement.Row, ExpectedElement.Row>
        ) {
            // Act & Assert: Check if the row's category matches the expected category
            testData.input.element.getCategory() shouldBe testData.expected.category
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(
            testData: TestCase<InputElement.Row, ExpectedElement.Row>
        ) {
            // Arrange: Create an empty style collection (default is empty)
            val emptyStyleCollection = ElementCategoryStyleCollectionImpl<ElementColorStyle>()
            // Act & Assert: Call getBackgroundColor, which internally calls getStyle and should throw an exception
            val row = testData.input.element

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

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideRowTestCases(rowsStandardTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each standard row in iRowStyle`(
            testData: TestCase<InputElement.Row, ExpectedElement.Row>
        ) {
            // Arrange:
            val style = StylesRow.iRowStyle
            // Act:
            val actualColor = testData.input.element.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideRowTestCases(rowsStandardTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground color for each standard row in iRowStyle`(
            testData: TestCase<InputElement.Row, ExpectedElement.Row>
        ) {
            // Arrange:
            val style = StylesRow.iRowStyle
            // Act:
            val actualColor = testData.input.element.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).foreground
        }
    }

    @Nested
    inner class GetButtons {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideRowTestCases(rowsStandardTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map buttons to rows`(
            testData: TestCase<InputElement.Row, ExpectedElement.Row>
        ) {
            // Act & Assert:
            (testData.expected).buttonData.shouldBeEqualToIgnoringFields((testData.input.element as Row).buttons, ButtonData::layout)
        }
    }
}