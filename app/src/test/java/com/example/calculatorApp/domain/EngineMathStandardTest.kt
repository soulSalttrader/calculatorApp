package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineMath
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.doubles.shouldBeNaN
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class EngineMathStandardTest {

    private lateinit var engineMath: EngineMath

    @BeforeEach
    fun setUp() {
        engineMath = EngineMathStandard()
    }

    @Nested
    inner class ApplySign {

        // Arrange:
        private fun provideArguments(): Stream<Arguments> {
            return TestArgumentsEngineMath.provideSignArguments()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the negative of a positive number`(
            number: Number,
            expected: Number
        ) {
            // Act & Assert:
            when (number) {
                is Int -> engineMath.applySign(number) shouldBe expected
                is Double -> engineMath.applySign(number) shouldBeEqual expected
                else -> throw IllegalArgumentException("Unsupported type.")
            }
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the positive of a negative number`(
            number: Number,
            expected: Number
        ) {
            // Act & Assert:
            when (number) {
                is Int -> engineMath.applySign(number) shouldBe expected
                is Double -> engineMath.applySign(number) shouldBeEqual expected
                else -> throw IllegalArgumentException("Unsupported type.")
            }
        }

        @Test
        fun `should return plus zero when input is zero`() {
            // Act & Assert:
            engineMath.applySign(0.0) shouldBe -0.0
            engineMath.applySign(-0.0) shouldBe 0.0
            engineMath.applySign(0) shouldBe -0
            engineMath.applySign(-0) shouldBe 0
        }
    }

    @Nested
    inner class ApplyPercentage {

        // Arrange:
        private fun provideWholeNumbers(): Stream<Arguments> {
            return TestArgumentsEngineMath.providesPercentArguments()
                .filter { args -> (args.get()[2] as Double) % 1.0 == 0.0 }
        }

        // Arrange:
        private fun provideDecimalNumbers(): Stream<Arguments> {
            return TestArgumentsEngineMath.providesPercentArguments()
                .filter { args -> (args.get()[2] as Double) % 1.0 != 0.0 }
        }
        // Arrange:
        private fun provideZeroNumbers(): Stream<Arguments> {
            return TestArgumentsEngineMath.providesPercentArguments()
                .filter { args -> (args.get()[2] as Double) == 0.0 }
        }

        @ParameterizedTest
        @MethodSource("provideWholeNumbers")
        fun `should convert a whole number to percentage`(
            operandLeft: Double?,
            operator: ButtonCalculatorBinary?,
            operandRight: Double,
            expectedNumber: Double,
        ) {
            // Act & Assert:
            engineMath.applyPercent(operandLeft, operator, operandRight).shouldBe(expectedNumber plusOrMinus (1e-9))
        }

        @ParameterizedTest
        @MethodSource("provideDecimalNumbers")
        fun `should convert a decimal number to percentage`(
            operandLeft: Double,
            operator: ButtonCalculatorBinary?,
            operandRight: Double,
            expectedNumber: Double,
        ) {
            // Act & Assert:
            engineMath.applyPercent(operandLeft, operator, operandRight).shouldBe(expectedNumber plusOrMinus (1e-9))
        }

        @ParameterizedTest
        @MethodSource("provideZeroNumbers")
        fun `should return zero when input is zero`(
            operandLeft: Double,
            operator: ButtonCalculatorBinary?,
            operandRight: Double,
            expectedNumber: Double,
        ) {
            // Act & Assert:
            engineMath.applyPercent(operandLeft, operator, operandRight).shouldBe(expectedNumber plusOrMinus (1e-9))
        }
    }

    @Nested
    inner class ApplyBinary {
        // Arrange:
        private fun provideAdditionArguments(): Stream<Arguments> {
            return TestArgumentsEngineMath.provideAdditionArguments()
        }

        // Arrange:
        private fun provideSubtractionArguments(): Stream<Arguments> {
            return TestArgumentsEngineMath.provideSubtractionArguments()
        }

        // Arrange:
        private fun provideMultiplicationArguments(): Stream<Arguments> {
            return TestArgumentsEngineMath.provideMultiplicationArguments()
        }

        // Arrange:
        private fun provideDivisionArguments(): Stream<Arguments> {
            return TestArgumentsEngineMath.provideDivisionArgument()
        }

        @ParameterizedTest
        @MethodSource("provideAdditionArguments")
        fun `should add two numbers correctly`(
            operandLeft: Double,
            operation: ButtonCalculatorBinary.Addition,
            operandRight: Double,
            expectedDouble: Double,
        ) {
            // Act & Assert:
            engineMath.applyArithmetic(operandLeft, operation, operandRight) shouldBe expectedDouble
        }

        @ParameterizedTest
        @MethodSource("provideSubtractionArguments")
        fun `should subtract two numbers correctly`(
            operandLeft: Double,
            operation: ButtonCalculatorBinary.Subtraction,
            operandRight: Double,
            expectedDouble: Double,
        ) {
            // Act & Assert:
            engineMath.applyArithmetic(operandLeft, operation, operandRight) shouldBe expectedDouble
        }

        @ParameterizedTest
        @MethodSource("provideMultiplicationArguments")
        fun `should multiply two numbers correctly`(
            operandLeft: Double,
            operation: ButtonCalculatorBinary.Multiplication,
            operandRight: Double,
            expectedDouble: Double,
        ) {
            // Act & Assert:
            engineMath.applyArithmetic(operandLeft, operation, operandRight).shouldBe(expectedDouble plusOrMinus(1e-9))
        }

        @ParameterizedTest
        @MethodSource("provideDivisionArguments")
        fun `should divide two numbers correctly`(
            operandLeft: Double,
            operation: ButtonCalculatorBinary.Division,
            operandRight: Double,
            expectedDouble: Double,
        ) {

            // Act & Assert:
            val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

            result.apply {
                if (expectedDouble.isNaN()) shouldBeNaN() else this shouldBe expectedDouble
            }
        }
    }
}