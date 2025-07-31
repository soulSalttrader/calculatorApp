package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsButton.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestDataButtonCalculatorX
import com.example.calculatorApp.utils.ButtonCalculatorList.unary
import com.example.calculatorApp.utils.ButtonCalculatorMappings.unaryVisualsMap
import com.example.calculatorApp.utils.VisualsButtonUnary
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonCalculatorUnaryTest {

    @Nested
    inner class GetCategory {

        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Stream<Button> = unary.asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each unary button`(button: ButtonCalculatorUnary) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Unary shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(button: ButtonCalculatorUnary) {
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
        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorUnary>> =
            provideMappedTestData(unary, unaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each unary button in iButtonStyle`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorUnary>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.button.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsButtonUnary).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorUnary>> =
            provideMappedTestData(unary, unaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground for each unary button in iButtonStyle`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorUnary>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.button.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsButtonUnary).foreground
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange:
        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorUnary>> =
            provideMappedTestData(unary, unaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorUnary>
        ) {
            // Act & Assert:
            testData.button.symbol shouldBe (testData.expected as VisualsButtonUnary).symbol
        }
    }


    @Nested
    inner class IsPrefix {

        // Arrange:
        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorUnary>> =
            provideMappedTestData(unary, unaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return true for prefix unary operation`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorUnary>
        ) {
            // Act & Assert:
            testData.button.isPrefix() shouldBe (testData.expected as VisualsButtonUnary).isPrefix
        }
    }

    @Nested
    inner class IsSuffix {

        // Arrange:
        private fun provideArguments(): Stream<TestDataButtonCalculatorX<ButtonCalculatorUnary>> =
            provideMappedTestData(unary, unaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return true for suffix unary operation`(
            testData: TestDataButtonCalculatorX<ButtonCalculatorUnary>
        ) {
            // Act & Assert:
            testData.button.isSuffix() shouldBe !(testData.expected as VisualsButtonUnary).isPrefix
        }
    }
}