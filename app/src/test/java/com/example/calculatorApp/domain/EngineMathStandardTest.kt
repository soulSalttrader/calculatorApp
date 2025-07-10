package com.example.calculatorApp.domain

import com.example.calculatorApp.testData.TestDataEngineMathStandardBinary
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent
import com.example.calculatorApp.testData.TestDataEngineMathStandardSign
import com.example.calculatorApp.arguments.TestArgumentsEngineMath
import com.example.calculatorApp.domain.ast.EvaluationResult
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class EngineMathStandardTest {

    private lateinit var engineMath: EngineMath

    @BeforeEach
    fun setUp() {
        engineMath = EngineMathStandard()
    }

    @Nested
    inner class EvalBinary {
        private fun provideBinaryArguments(): Stream<TestDataEngineMathStandardBinary> =
            TestArgumentsEngineMath.provideBinary().take(5).asStream()

        private fun provideBinaryArgumentsAll(): Stream<TestDataEngineMathStandardBinary> =
            TestArgumentsEngineMath.provideBinary().take(20).asStream()

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsAll")
        fun `should apply binary operator correctly`(
            testData: TestDataEngineMathStandardBinary
        ) {
            // Act & Assert:
            engineMath.evalBinary(
                testData.leftOperand,
                testData.rightOperand,
                testData.operation
            ) shouldBe testData.expectedResult(testData.operation)
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArguments")
        fun `should add two numbers correctly`(
            testData: TestDataEngineMathStandardBinary
        ) {
            // Arrange:
            val operation: BinaryOperation = { l, r -> l + r }
            // Act & Assert:
            engineMath.evalBinary(
                testData.leftOperand,
                testData.rightOperand,
                operation
            ) shouldBe testData.expectedAddition()
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArguments")
        fun `should subtract two numbers correctly`(
            testData: TestDataEngineMathStandardBinary
        ) {
            // Arrange:
            val operation: BinaryOperation = { l, r -> l - r }
            // Act & Assert:
            engineMath.evalBinary(
                testData.leftOperand,
                testData.rightOperand,
                operation
            ) shouldBe testData.expectedSubtraction()
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArguments")
        fun `should multiply two numbers correctly`(
            testData: TestDataEngineMathStandardBinary
        ) {
            // Arrange:
            val operation: BinaryOperation = { l, r -> l * r }
            // Act & Assert:
            engineMath.evalBinary(
                testData.leftOperand,
                testData.rightOperand,
                operation
            ) shouldBe testData.expectedMultiplication()
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsAll")
        fun `should divide two numbers correctly`(
            testData: TestDataEngineMathStandardBinary
        ) {
            // Arrange:
            val operation: BinaryOperation = { l, r -> l / r }
            // Act & Assert:
            engineMath.evalBinary(
                testData.leftOperand,
                testData.rightOperand,
                operation
            ) shouldBe testData.expectedDivision()
        }
    }

    @Nested
    inner class EvalPercentage {
        // Arrange:
        private fun providePercentArgumentsWhole(): Stream<TestDataEngineMathStandardPercent> =
            TestArgumentsEngineMath.providePercent().asStream()
                .filter { it.operand % 1.0 == 0.0 && it.operand != 0.0 }
        // Arrange:
        private fun providePercentArgumentsDecimal(): Stream<TestDataEngineMathStandardPercent> =
            TestArgumentsEngineMath.providePercent().asStream()
                .filter { it.operand % 1.0 != 0.0 }
        // Arrange:
        private fun providePercentArgumentsZero(): Stream<TestDataEngineMathStandardPercent> =
            TestArgumentsEngineMath.providePercent().asStream()
                .filter { it.operand == 0.0 }

        @ParameterizedTest
        @MethodSource("providePercentArgumentsWhole")
        fun `should convert a whole number to percentage`(
            testData: TestDataEngineMathStandardPercent
        ) {
            // Act & Assert:
            engineMath.evalPercent(
                testData.operand,
                testData.previousNumber,
                testData.lastOperator,
            ) shouldBe testData.expectedWhole()[testData]
        }

        @ParameterizedTest
        @MethodSource("providePercentArgumentsDecimal")
        fun `should convert a decimal number to percentage`(
            testData: TestDataEngineMathStandardPercent
        ) {
            // Act & Assert:
            engineMath.evalPercent(
                testData.operand,
                testData.previousNumber,
                testData.lastOperator,
            ) shouldBe testData.expectedDecimal()[testData]
        }

        @ParameterizedTest
        @MethodSource("providePercentArgumentsZero")
        fun `should return zero when input is zero`(
            testData: TestDataEngineMathStandardPercent
        ) {
            // Act & Assert:
            engineMath.evalPercent(
                testData.operand,
                testData.previousNumber,
                testData.lastOperator,
            ) shouldBe testData.expectedZero()
        }
    }

    @Nested
    inner class EvalSign {
        // Arrange:
        private fun provideArgumentsPositive(): Stream<TestDataEngineMathStandardSign> =
            TestArgumentsEngineMath.provideSign().asStream()
                .filter { it.operand > 0 }
        // Arrange:
        private fun provideArgumentsNegative(): Stream<TestDataEngineMathStandardSign> =
            TestArgumentsEngineMath.provideSign().asStream()
                .filter { it.operand < 0 }
        // Arrange:
        private fun provideArgumentsZero(): Stream<TestDataEngineMathStandardSign> =
            TestArgumentsEngineMath.provideSign().asStream()
                .filter { it.operand == 0.0 }


        @ParameterizedTest
        @MethodSource("provideArgumentsPositive")
        fun `should return the negative of a positive number`(
            testData: TestDataEngineMathStandardSign
        ) {
            // Act & Assert:
            val toggled = engineMath.evalSign(testData.operand)

            when (toggled.value) {
                is Long -> toggled shouldBe EvaluationResult.IntegerResult(-testData.operand.toLong())
                is Double -> toggled shouldBe EvaluationResult.DoubleResult(-testData.operand)
            }
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNegative")
        fun `should return the positive of a negative number`(
            testData: TestDataEngineMathStandardSign
        ) {
            // Act & Assert:
            val toggled = engineMath.evalSign(testData.operand)

            when (toggled.value) {
                is Long -> toggled shouldBe EvaluationResult.IntegerResult(-testData.operand.toLong())
                is Double -> toggled shouldBe EvaluationResult.DoubleResult(-testData.operand)
            }
        }
        @ParameterizedTest
        @MethodSource("provideArgumentsZero")
        fun `should return plus zero when input is zero`(
            testData: TestDataEngineMathStandardSign
        ) {
            // Act & Assert:
            val toggled = engineMath.evalSign(testData.operand)

            when (toggled.value) {
                is Long -> toggled shouldBe EvaluationResult.IntegerResult(-testData.operand.toLong())
                is Double -> toggled shouldBe EvaluationResult.DoubleResult(-testData.operand)
            }
        }
    }
}