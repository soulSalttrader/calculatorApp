package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.styles.StylesRow
import com.example.calculatorApp.testData.TestDataElementExpectedMap
import com.example.calculatorApp.testData.TestDataElementSeq
import com.example.calculatorApp.testData.TestDataCalculatorElement
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.expected.ExpectedRow
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
        private fun provideArguments(): Stream<Row> = TestDataElementSeq.rowsStandardTest.asStream()

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

        // Arrange:
        private fun provideArguments(): Stream<TestDataCalculatorElement<Row, ExpectedElement>> =
            provideMappedTestData(TestDataElementSeq.rowsStandardTest, TestDataElementExpectedMap.standardExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each standard row in iRowStyle`(
            testData: TestDataCalculatorElement<Row, ExpectedElement>
        ) {
            // Arrange:
            val style = StylesRow.iRowStyle
            // Act:
            val actualColor = testData.element.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestDataCalculatorElement<Row, ExpectedElement>> =
            provideMappedTestData(TestDataElementSeq.rowsStandardTest, TestDataElementExpectedMap.standardExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground color for each standard row in iRowStyle`(
            testData: TestDataCalculatorElement<Row, ExpectedElement>
        ) {
            // Arrange:
            val style = StylesRow.iRowStyle
            // Act:
            val actualColor = testData.element.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).foreground
        }
    }

    @Nested
    inner class GetButtons {

        // Arrange:
        private fun provideArguments(): Stream<TestDataCalculatorElement<Row, ExpectedElement>> =
            provideMappedTestData(TestDataElementSeq.rowsStandardTest, TestDataElementExpectedMap.standardExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map buttons to rows`(
            testData: TestDataCalculatorElement<Row, ExpectedElement>
        ) {
            // Act & Assert:
            (testData.expected as ExpectedRow).buttonData.shouldBeEqualToIgnoringFields(testData.element.buttons, ButtonData::layout)
        }
    }
}