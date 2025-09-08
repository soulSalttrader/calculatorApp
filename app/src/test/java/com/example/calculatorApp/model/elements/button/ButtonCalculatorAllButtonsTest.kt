package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideButtonTestCases
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElement.buttonsAllTest
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputElement
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonCalculatorAllButtonsTest {

    @Nested
    inner class GetCategory {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideButtonTestCases(buttonsAllTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each calculator button`(
            testData: TestCase<InputElement.Button, ExpectedElement.Button>
        ) {
            // Act & Assert: Check if the button's category matches the expected category
            val button = testData.input.element
            button.getCategory() shouldBe testData.expected.category
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(
            testData: TestCase<InputElement.Button, ExpectedElement.Button>
        ) {
            // Arrange:
            val emptyStyleCollection = ElementCategoryStyleCollectionImpl<ElementColorStyle>()
            // Act & Assert:
            val button = testData.input.element

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
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideButtonTestCases(buttonsAllTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each calculator button in iButtonStyle`(
            testData: TestCase<InputElement.Button, ExpectedElement.Button>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = (testData.input.element).getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideButtonTestCases(buttonsAllTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground for each arithmetic button in iButtonStyle`(
            testData: TestCase<InputElement.Button, ExpectedElement.Button>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = (testData.input.element).getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).foreground
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideButtonTestCases(buttonsAllTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestCase<InputElement.Button, ExpectedElement.Button>
        ) {
            // Act & Assert:
            (testData.input.element as Button).symbol shouldBe (testData.expected).symbol
        }
    }
}