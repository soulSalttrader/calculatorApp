package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.annotations.ConceptClass
import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideDisplayTestCases
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesDisplay
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementSeq.displaysInputsTest
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

class DisplayCalculatorInputTest {

    @Nested
    inner class GetCategory {

        // Arrange:
        @OptIn(ConceptClass::class)
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideDisplayTestCases(displaysInputsTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each input display`(
            testData: TestCase<InputElement.Display, ExpectedElement.Display>
        ) {
            // Act & Assert: Check if the display's category matches the expected category
            testData.input.element.getCategory() shouldBe testData.expected.category
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(
            testData: TestCase<InputElement.Display, ExpectedElement.Display>
        ) {
            // Arrange: Create an empty style collection (default is empty)
            val emptyStyleCollection = ElementCategoryStyleCollectionImpl<ElementColorStyle>()
            // Act & Assert: Call getBackgroundColor, which internally calls getStyle and should throw an exception
            val display = testData.input.element

            display.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    display.getBackgroundColor(emptyStyleCollection)
                }
                // Assert: Validate the exception message
                exception.message shouldBe "Category '${display.getCategory()}' not found."
            }
        }
    }

    @Nested
    inner class GetBackgroundColor {

        // Arrange:
        @OptIn(ConceptClass::class)
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideDisplayTestCases(displaysInputsTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each input display in iDisplayStyle`(
            testData: TestCase<InputElement.Display, ExpectedElement.Display>
        ) {
            // Arrange:
            val style = StylesDisplay.iDisplayStyleInput
            // Act:
            val actualColor = testData.input.element.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        @OptIn(ConceptClass::class)
        private fun provideArguments(): Stream<TestCase<Input, Expected>> =
            provideDisplayTestCases(displaysInputsTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground color for each input display in iDisplayStyle`(
            testData: TestCase<InputElement.Display, ExpectedElement.Display>
        ) {
            // Arrange:
            val style = StylesDisplay.iDisplayStyleInput
            // Act:
            val actualColor = testData.input.element.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected).foreground
        }
    }
}