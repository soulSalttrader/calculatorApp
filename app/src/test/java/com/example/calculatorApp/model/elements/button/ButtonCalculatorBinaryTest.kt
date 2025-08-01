package com.example.calculatorApp.model.elements.button

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesButton
import com.example.calculatorApp.testData.TestDataCalculatorElement
import com.example.calculatorApp.utils.ButtonCalculatorList.binary
import com.example.calculatorApp.utils.ButtonCalculatorMappings.binaryVisualsMap
import com.example.calculatorApp.utils.VisualsButton
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class ButtonCalculatorBinaryTest {

    @Nested
    inner class GetCategory {

        // Arrange: Setup test data (button instance)
        private fun provideArguments(): Stream<Button> = binary.asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each arithmetic button`(button: ButtonCalculatorBinary) {
            // Act & Assert: Check if the button's category matches the expected category
            ButtonCategory.Binary shouldBe button.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(button: ButtonCalculatorBinary) {
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
        private fun provideArguments(): Stream<TestDataCalculatorElement<ButtonCalculatorBinary>> =
            provideMappedTestData(binary, binaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each arithmetic button in iButtonStyle`(
            testData: TestDataCalculatorElement<ButtonCalculatorBinary>
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

        private fun provideArguments(): Stream<TestDataCalculatorElement<ButtonCalculatorBinary>> =
            provideMappedTestData(binary, binaryVisualsMap).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground for each arithmetic button in iButtonStyle`(
            testData: TestDataCalculatorElement<ButtonCalculatorBinary>
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

        // Arrange: Setup test data (button instance and expected symbol)
        private fun provideArguments(): Stream<TestDataCalculatorElement<ButtonCalculatorBinary>> =
            provideMappedTestData(binary, binaryVisualsMap).asStream()


        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should correctly map symbols to buttons`(
            testData: TestDataCalculatorElement<ButtonCalculatorBinary>
        ) {
            // Act & Assert: Check if the button's symbol matches the expected symbol
            testData.element.symbol shouldBe (testData.expected as VisualsButton).symbol
        }
    }
}