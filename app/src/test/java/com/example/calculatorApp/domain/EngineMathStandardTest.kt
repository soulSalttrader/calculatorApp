package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineMath
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import io.kotest.assertions.throwables.shouldThrow
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
            double: Double,
            expectedDouble: Double
        ) {
            // Act & Assert:
            engineMath.applySign(double) shouldBeEqual expectedDouble
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should return the positive of a negative number`(
            double: Double,
            expectedDouble: Double
        ) {
            // Act & Assert:
            engineMath.applySign(double) shouldBeEqual expectedDouble
        }

        @Test
        fun `should return plus zero when input is zero`() {
            // Act & Assert:
            engineMath.applySign(0.0) shouldBe -0.0
            engineMath.applySign(-0.0) shouldBe 0.0
        }
    }

    @Nested
    inner class ApplyPercent {

        // Arrange:
        private fun provideWholeNumbers(): Stream<Arguments> {
            return TestArgumentsEngineMath.providesPercentArguments()
                .filter { args -> (args.get()[0] as Double) % 1.0 == 0.0 }
        }

        // Arrange:
        private fun provideDecimalNumbers(): Stream<Arguments> {
            return TestArgumentsEngineMath.providesPercentArguments()
                .filter { args -> (args.get()[0] as Double) % 1.0 != 0.0 }
        }
        // Arrange:
        private fun provideZeroNumbers(): Stream<Arguments> {
            return TestArgumentsEngineMath.providesPercentArguments()
                .filter { args -> (args.get()[0] as Double) == 0.0 }
        }

        @ParameterizedTest
        @MethodSource("provideWholeNumbers")
        fun `should convert a whole number to percentage`(
            number: Double,
            expectedNumber: Double,
        ) {
            // Act & Assert:
            engineMath.applyPercent(number) shouldBe expectedNumber
        }

        @ParameterizedTest
        @MethodSource("provideDecimalNumbers")
        fun `should convert a decimal number to percentage`(
            number: Double,
            expectedNumber: Double,
        ) {
            // Act & Assert:
            engineMath.applyPercent(number) shouldBe expectedNumber
        }

        @ParameterizedTest
        @MethodSource("provideZeroNumbers")
        fun `should return zero when input is zero`(
            number: Double,
            expectedNumber: Double,
        ) {
            // Act & Assert:
            engineMath.applyPercent(number) shouldBe expectedNumber
        }
    }

    @Nested
    inner class ApplyArithmetic {
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
            operation: ButtonCalculatorArithmetic.Addition,
            left: Double,
            right: Double,
            expectedDouble: Double,
        ) {
            // Act & Assert:
            engineMath.applyArithmetic(left, right, operation) shouldBe expectedDouble
        }

        @ParameterizedTest
        @MethodSource("provideSubtractionArguments")
        fun `should subtract two numbers correctly`(
            operation: ButtonCalculatorArithmetic.Subtraction,
            left: Double,
            right: Double,
            expectedDouble: Double,
        ) {
            // Act & Assert:
            engineMath.applyArithmetic(left, right, operation) shouldBe expectedDouble
        }

        @ParameterizedTest
        @MethodSource("provideMultiplicationArguments")
        fun `should multiply two numbers correctly`(
            operation: ButtonCalculatorArithmetic.Multiplication,
            left: Double,
            right: Double,
            expectedDouble: Double,
        ) {
            // Act & Assert:
            engineMath.applyArithmetic(left, right, operation).shouldBe(expectedDouble plusOrMinus(1e-9))
        }

        @ParameterizedTest
        @MethodSource("provideDivisionArguments")
        fun `should divide two numbers correctly`(
            operation: ButtonCalculatorArithmetic.Division,
            left: Double,
            right: Double,
            expectedDouble: Double,
        ) {

            // Act & Assert:
            val result = engineMath.applyArithmetic(left, right, operation)

            result.apply {
                if (expectedDouble.isNaN()) shouldBeNaN() else this shouldBe expectedDouble
            }
        }

        @Test
        fun `should throw an exception for an unknown operation`() {
            // Arrange:
            val left = 1.0
            val right = 2.0
            val unknownOperation = ButtonCalculatorArithmetic.Equals

            // Act:
            val exception = shouldThrow<IllegalArgumentException> {
                engineMath.applyArithmetic(left, right, unknownOperation)
            }

            // Assert: Validate the exception message
            exception.message shouldBe "Unknown operation."
        }
    }
}