package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestDataElementExpectedMap.numberExpectedMapTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsNumbersTest
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonCalculatorNumberTest {

    @Nested
    inner class GetCategory {

        // Arrange:
        private fun provideArguments(): Stream<Button> = buttonsNumbersTest.asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each number button`(button: ButtonCalculatorNumber) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Number shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(button: ButtonCalculatorNumber) {
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

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Button, Expected>> =
            provideMappedTestData(buttonsNumbersTest, numberExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each number button in iButtonStyle`(
            testData: TestCase<Button, ExpectedElement.Button>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.input.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Button, Expected>> =
            provideMappedTestData(buttonsNumbersTest, numberExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground color for each number button in iButtonStyle`(
            testData: TestCase<Button, ExpectedElement.Button>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.input.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).foreground
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Button, Expected>> =
            provideMappedTestData(buttonsNumbersTest, numberExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestCase<Button, ExpectedElement.Button>
        ) {
            // Act & Assert:
            testData.input.symbol shouldBe (testData.expected).symbol
        }
    }
}