package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsButton
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestDataButtonCalculatorX
import com.example.calculatorApp.utils.ButtonCalculatorList.controls
import com.example.calculatorApp.utils.ButtonCalculatorMappings.controlVisualsMap
import com.example.calculatorApp.utils.VisualsButton
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonCalculatorControlTest {

    @Nested
    inner class GetCategory {

        // Arrange:
        private fun provideArguments(): Stream<Button> = controls.asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each control button`(button: ButtonCalculatorControl) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Control shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(button: ButtonCalculatorControl) {
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
        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorControl>> =
            TestArgumentsButton.provideMappedTestData(controls, controlVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each control button in iButtonStyle`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorControl>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.button.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsButton).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorControl>> =
            TestArgumentsButton.provideMappedTestData(controls, controlVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground for each control button in iButtonStyle`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorControl>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.button.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsButton).foreground
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange:
        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorControl>> =
            TestArgumentsButton.provideMappedTestData(controls, controlVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorControl>
        ) {
            // Act & Assert: Check if the button's symbol matches the expected symbol
            testData.button.symbol shouldBe (testData.expected as VisualsButton).symbol
        }
    }
}