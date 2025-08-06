package com.example.calculatorApp.model.elements.display

import com.example.calculatorApp.arguments.TestArgumentsCalculatorElement.provideMappedTestData
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesDisplay
import com.example.calculatorApp.testData.TestDataElementExpectedMap
import com.example.calculatorApp.testData.TestDataElementSeq
import com.example.calculatorApp.testData.TestDataCalculatorElement
import com.example.calculatorApp.utils.Visuals
import com.example.calculatorApp.utils.VisualsDisplay
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
        private fun provideArguments(): Stream<DisplayCalculatorInput> =
            TestDataElementSeq.displaysInputsTest.asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right category for each input display`(display: DisplayCalculatorInput) {
            // Act & Assert: Check if the display's category matches the expected category
            DisplayCategory.Input shouldBe display.getCategory()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should throw exception when category is not found`(display: DisplayCalculatorInput) {
            // Arrange: Create an empty style collection (default is empty)
            val emptyStyleCollection = ElementCategoryStyleCollectionImpl<ElementColorStyle>()
            // Act & Assert: Call getBackgroundColor, which internally calls getStyle and should throw an exception
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
        private fun provideArguments(): Stream<TestDataCalculatorElement<Display, Visuals>> =
            provideMappedTestData(TestDataElementSeq.displaysInputsTest, TestDataElementExpectedMap.inputsVisualsMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each input display in iDisplayStyle`(
            testData: TestDataCalculatorElement<Display, Visuals>
        ) {
            // Arrange:
            val style = StylesDisplay.iDisplayStyleInput
            // Act:
            val actualColor = testData.element.getBackgroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsDisplay).background
        }
    }

    @Nested
    inner class GetForegroundColor {

        private fun provideArguments(): Stream<TestDataCalculatorElement<Display, Visuals>> =
            provideMappedTestData(TestDataElementSeq.displaysInputsTest, TestDataElementExpectedMap.inputsVisualsMapTest).asStream()

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right foreground color for each input display in iDisplayStyle`(
            testData: TestDataCalculatorElement<Display, Visuals>
        ) {
            // Arrange:
            val style = StylesDisplay.iDisplayStyleInput
            // Act:
            val actualColor = testData.element.getForegroundColor(style)
            // Assert:
            actualColor shouldBe (testData.expected as VisualsDisplay).foreground
        }
    }
}