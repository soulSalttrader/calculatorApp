package com.example.calculatorApp.model.elements.button

import androidx.compose.ui.graphics.toArgb
import com.example.calculatorApp.arguments.TestArgumentsButtonStyleBuilder
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.utils.ColorToLongConverter
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class ButtonCategoryStyleBuilderTest {

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class ArithmeticStyle {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder().provideArithmeticStyle()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply arithmetic style correctly`(
            button: ButtonCalculatorArithmetic,
            style: ElementCategoryStyleCollection<ElementColorStyle>,
            @ConvertWith(ColorToLongConverter::class) expectedBackgroundColor: Long,
            @ConvertWith(ColorToLongConverter::class) expectedTextColor: Long,
        ) {

            val actualBackgroundColor = button.getBackgroundColor(style).toArgb()
            val actualTextColor = button.getTextColor(style).toArgb()

            expectedBackgroundColor shouldBe actualBackgroundColor
            expectedTextColor shouldBe actualTextColor
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class ControlStyle {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder().provideControlStyle()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply control style correctly with default and decimal style`(
            button: ButtonCalculatorControl,
            style: ElementCategoryStyleCollection<ElementColorStyle>,
            @ConvertWith(ColorToLongConverter::class) expectedBackgroundColor: Long,
            @ConvertWith(ColorToLongConverter::class) expectedTextColor: Long,
        ) {
            val actualBackgroundColor = button.getBackgroundColor(style).toArgb()
            val actualTextColor = button.getTextColor(style).toArgb()

            // Compare the actual and expected colors
            expectedBackgroundColor shouldBe actualBackgroundColor
            expectedTextColor shouldBe actualTextColor
        }
    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    inner class NumberStyle {

        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsButtonStyleBuilder().provideNumberStyle()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should apply number style correctly`(
            button: ButtonCalculatorNumber,
            style: ElementCategoryStyleCollection<ElementColorStyle>,
            @ConvertWith(ColorToLongConverter::class) expectedBackgroundColor: Long,
            @ConvertWith(ColorToLongConverter::class) expectedTextColor: Long,
        ) {

            val actualBackgroundColor = button.getBackgroundColor(style).toArgb()
            val actualTextColor = button.getTextColor(style).toArgb()

            expectedBackgroundColor shouldBe actualBackgroundColor
            expectedTextColor shouldBe actualTextColor
        }
    }

//    @Nested
//    inner class Build() {
//        fun `should build and return the expected style collection`() {
//
//        }
//    }
}