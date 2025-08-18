package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestDataElementExpectedMap.unaryExpectedMapTest
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsUnaryTest
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.expected.ExpectedElement
import com.example.calculatorApp.testData.expected.ExpectedButtonUnary
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
        private fun provideArguments(): Stream<Button> = buttonsUnaryTest.asStream()

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
        private fun provideArguments(): Stream<TestCase<Button, ExpectedElement>> =
            provideMappedTestData(buttonsUnaryTest, unaryExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each unary button in iButtonStyle`(
            testData: TestCase<Button, ExpectedElement>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.input.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as ExpectedButtonUnary).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestCase<Button, ExpectedElement>> =
            provideMappedTestData(buttonsUnaryTest, unaryExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground color for each unary button in iButtonStyle`(
            testData: TestCase<Button, ExpectedElement>
        ) {
            // Arrange:
            val style = StylesButton.iButtonStyle
            // Act:
            val actualColor = testData.input.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as ExpectedButtonUnary).foreground
        }
    }

    @Nested
    inner class GetSymbol {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Button, ExpectedElement>> =
            provideMappedTestData(buttonsUnaryTest, unaryExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestCase<Button, ExpectedElement>
        ) {
            // Act & Assert:
            testData.input.symbol shouldBe (testData.expected as ExpectedButtonUnary).symbol
        }
    }


    @Nested
    inner class IsPrefix {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Button, ExpectedElement>> =
            provideMappedTestData(buttonsUnaryTest, unaryExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return true for prefix unary operation`(
            testData: TestCase<Button, ExpectedElement>
        ) {
            // Act & Assert:
            (testData.input as ButtonCalculatorUnary).isPrefix() shouldBe (testData.expected as ExpectedButtonUnary).isPrefix
        }
    }

    @Nested
    inner class IsSuffix {

        // Arrange:
        private fun provideArguments(): Stream<TestCase<Button, ExpectedElement>> =
            provideMappedTestData(buttonsUnaryTest, unaryExpectedMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return true for suffix unary operation`(
            testData: TestCase<Button, ExpectedElement>
        ) {
            // Act & Assert:
            (testData.input as ButtonCalculatorUnary).isSuffix() shouldBe !(testData.expected as ExpectedButtonUnary).isPrefix
        }
    }
}