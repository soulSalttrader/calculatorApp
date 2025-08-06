package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestDataElementExpectedMap.parenthesisVisualsMap
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsParenthesisTest
import com.example.calculatorApp.testData.TestDataCalculatorElement
import com.example.calculatorApp.utils.Visuals
import com.example.calculatorApp.utils.VisualsButton
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonCalculatorParenthesisTest {

    @Nested
    inner class GetCategory {

        // Arrange:
        private fun provideArguments(): Stream<Button> = buttonsParenthesisTest.asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each parenthesis button`(button: ButtonCalculatorParenthesis) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Parenthesis shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(button: ButtonCalculatorParenthesis) {
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
        private fun provideArguments(): Stream<TestDataCalculatorElement<Button, Visuals>> =
            provideMappedTestData(buttonsParenthesisTest, parenthesisVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each parenthesis button in iButtonStyle`(
            testData: TestDataCalculatorElement<Button, Visuals>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.element.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsButton).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestDataCalculatorElement<Button, Visuals>> =
            provideMappedTestData(buttonsParenthesisTest, parenthesisVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground for each parenthesis button in iButtonStyle`(
            testData: TestDataCalculatorElement<Button, Visuals>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.element.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsButton).foreground
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange:
        private fun provideArguments(): Stream<TestDataCalculatorElement<Button, Visuals>> =
            provideMappedTestData(buttonsParenthesisTest, parenthesisVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestDataCalculatorElement<Button, Visuals>
        ) {
            // Act & Assert:
            testData.element.symbol shouldBe (testData.expected as VisualsButton).symbol
        }
    }
}