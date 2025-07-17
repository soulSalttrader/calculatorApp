package com.example.calculatorApp.model.elements.display

import TestArgumentsDisplay
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollectionImpl
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.styles.StylesDisplay
import com.example.calculatorApp.utils.ColorToLongConverter
import com.example.calculatorApp.utils.DisplayCalculatorList
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class DisplayCalculatorInputTest {

    @Nested
    inner class GetCategory {

        // Arrange: Setup test data (display instance)
        private fun provideArguments(): Array<DisplayCalculatorInput> {
            return DisplayCalculatorList.inputs
        }

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

        // Arrange: Setup test data (display instance)
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsDisplay.provideInputsColors()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right backgroundColor for each input display`(
            display: DisplayCalculatorInput,
            @ConvertWith(ColorToLongConverter::class) expectedColor: Long,
        ) {
            // Arrange:
            val style = StylesDisplay.iDisplayStyleInput

            // Act:
            val actualColor = display.getBackgroundColor(style).toArgb()

            // Assert:
            actualColor shouldBe expectedColor
        }
    }

    @Nested
    inner class GetTextColor {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsDisplay.provideInputsColors()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should get the right textColor for each input display`(
            display: DisplayCalculatorInput,
            @ConvertWith(ColorToLongConverter::class) expectedColor: Long,
        ) {
            // Arrange:
            val style = StylesDisplay.iDisplayStyleInput

            // Act:
            val actualColor = display.getBackgroundColor(style).toArgb()

            // Assert:
            actualColor shouldBe expectedColor
        }
    }
}