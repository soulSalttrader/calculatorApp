package com.example.calculatorApp.domain

import com.example.calculatorApp.arguments.TestArgumentsEngineMathStandard.provideEngineMathBinaryTestCases
import com.example.calculatorApp.arguments.TestArgumentsEngineMathStandard.provideEngineMathPercentageTestCases
import com.example.calculatorApp.arguments.TestArgumentsEngineMathStandard.provideEngineMathSignTestCases
import com.example.calculatorApp.domain.ast.BinaryOperations
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent.hasDecimalNonZeroOperands
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent.hasWholeNonZeroOperands
import com.example.calculatorApp.testData.TestDataEngineMathStandardPercent.hasZerosOperands
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputEngineMath
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
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
        private fun provideBinaryArgumentsAll(): Stream<TestCase<Input, Expected>> =
            provideEngineMathBinaryTestCases()
                .asStream()

        private fun provideBinaryArgumentsAdd(): Stream<TestCase<Input, Expected>> =
            provideEngineMathBinaryTestCases()
                .filter { (it.input as InputEngineMath.Binary).operation == BinaryOperations.Add }
                .asStream()

        private fun provideBinaryArgumentsSub(): Stream<TestCase<Input, Expected>> =
            provideEngineMathBinaryTestCases()
                .filter { (it.input as InputEngineMath.Binary).operation == BinaryOperations.Sub }
                .asStream()

        private fun provideBinaryArgumentsMul(): Stream<TestCase<Input, Expected>> =
            provideEngineMathBinaryTestCases()
                .filter { (it.input as InputEngineMath.Binary).operation == BinaryOperations.Mul }
                .asStream()

        private fun provideBinaryArgumentsDiv(): Stream<TestCase<Input, Expected>> =
            provideEngineMathBinaryTestCases()
                .filter { (it.input as InputEngineMath.Binary).operation == BinaryOperations.Div }
                .asStream()

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsAll")
        fun `should apply binary operator correctly`(
            testData: TestCase<InputEngineMath.Binary, ExpectedEngineMath.Binary>
        ) {
            // Act & Assert:
            val evalBinary = engineMath.evalBinary(
                testData.input.leftOperand,
                testData.input.rightOperand,
                testData.input.operation
            )
                evalBinary.value shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsAdd")
        fun `should add two numbers correctly`(
            testData: TestCase<InputEngineMath.Binary, ExpectedEngineMath.Binary>
        ) {
            // Act & Assert:
            val evalBinary = engineMath.evalBinary(
                testData.input.leftOperand,
                testData.input.rightOperand,
                testData.input.operation
            )
            evalBinary.value shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsSub")
        fun `should subtract two numbers correctly`(
            testData: TestCase<InputEngineMath.Binary, ExpectedEngineMath.Binary>
        ) {
            // Act & Assert:
            val evalBinary = engineMath.evalBinary(
                testData.input.leftOperand,
                testData.input.rightOperand,
                testData.input.operation
            )
            evalBinary.value shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsMul")
        fun `should multiply two numbers correctly`(
            testData: TestCase<InputEngineMath.Binary, ExpectedEngineMath.Binary>
        ) {
            // Act & Assert:
            val evalBinary = engineMath.evalBinary(
                testData.input.leftOperand,
                testData.input.rightOperand,
                testData.input.operation
            )
            evalBinary.value shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("provideBinaryArgumentsDiv")
        fun `should divide two numbers correctly`(
            testData: TestCase<InputEngineMath.Binary, ExpectedEngineMath.Binary>
        ) {
            // Act & Assert:
            val evalBinary = engineMath.evalBinary(
                testData.input.leftOperand,
                testData.input.rightOperand,
                testData.input.operation
            )
            evalBinary.value shouldBe testData.expected.value
        }

        @Test
        fun `should throw Error when dividing by zero`() {
            // Act & Assert:
            val exception = shouldThrow<IllegalArgumentException> {
                engineMath.evalBinary(
                    10.0,
                    0.0,
                    BinaryOperations.Div
                )
            }

            exception.message shouldBe "NaN"
        }
    }

    @Nested
    inner class EvalPercentage {
        // Arrange:
        private fun providePercentArgumentsWhole(): Stream<TestCase<Input, Expected>> =
            provideEngineMathPercentageTestCases()
                .filter { (it.input as InputEngineMath.Percentage).hasWholeNonZeroOperands() }
                .asStream()

        // Arrange:
        private fun providePercentArgumentsDecimal(): Stream<TestCase<Input, Expected>> =
            provideEngineMathPercentageTestCases()
                .filter { (it.input as InputEngineMath.Percentage).hasDecimalNonZeroOperands() }
                .asStream()

        // Arrange:
        private fun providePercentArgumentsZero(): Stream<TestCase<Input, Expected>> =
            provideEngineMathPercentageTestCases()
                .filter { (it.input as InputEngineMath.Percentage).hasZerosOperands() }
                .asStream()

        @ParameterizedTest
        @MethodSource("providePercentArgumentsWhole")
        fun `should convert a whole number to percentage`(
            testData: TestCase<InputEngineMath.Percentage, ExpectedEngineMath.Percentage>
        ) {
            // Act & Assert:
            val evalPercent = engineMath.evalPercent(
                testData.input.operand,
                testData.input.previousNumber,
                testData.input.lastOperator,
            ).value
            evalPercent shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("providePercentArgumentsDecimal")
        fun `should convert a decimal number to percentage`(
            testData: TestCase<InputEngineMath.Percentage, ExpectedEngineMath.Percentage>
        ) {
            // Act & Assert:
            val evalPercent = engineMath.evalPercent(
                testData.input.operand,
                testData.input.previousNumber,
                testData.input.lastOperator,
            ).value
            evalPercent shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("providePercentArgumentsZero")
        fun `should return zero when input is zero`(
            testData: TestCase<InputEngineMath.Percentage, ExpectedEngineMath.Percentage>
        ) {
            // Act & Assert:
            val evalPercent = engineMath.evalPercent(
                testData.input.operand,
                testData.input.previousNumber,
                testData.input.lastOperator,
            ).value
            evalPercent shouldBe testData.expected.value
        }
    }

    @Nested
    inner class EvalSign {
        // Arrange:
        private fun provideArgumentsPositive(): Stream<TestCase<Input, Expected>> =
            provideEngineMathSignTestCases()
                .filter { (it.input as InputEngineMath.Sign).operand.toDouble() > 0.0 }
                .distinctBy { (it.input as InputEngineMath.Sign).operand.toDouble() }
                .asStream()

        // Arrange:
        private fun provideArgumentsNegative(): Stream<TestCase<Input, Expected>> =
            provideEngineMathSignTestCases()
                .filter { (it.input as InputEngineMath.Sign).operand.toDouble() < 0.0 }
                .distinctBy { (it.input as InputEngineMath.Sign).operand.toDouble() }
                .asStream()

        // Arrange:
        private fun provideArgumentsZero(): Stream<TestCase<Input, Expected>> =
            provideEngineMathSignTestCases()
                .filter { (it.input as InputEngineMath.Sign).operand.toDouble() == 0.0 }
                .distinctBy { (it.input as InputEngineMath.Sign).operand.toDouble() }
                .asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsPositive")
        fun `should return the negative of a positive number`(
            testData: TestCase<InputEngineMath.Sign, ExpectedEngineMath.Sign>
        ) {
            // Act & Assert:
            val sign = engineMath.evalSign(testData.input.operand)
            sign.value shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNegative")
        fun `should return the positive of a negative number`(
            testData: TestCase<InputEngineMath.Sign, ExpectedEngineMath.Sign>
        ) {
            // Act & Assert:
            val sign = engineMath.evalSign(testData.input.operand)
            sign.value shouldBe testData.expected.value
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsZero")
        fun `should return plus zero when input is zero`(
            testData: TestCase<InputEngineMath.Sign, ExpectedEngineMath.Sign>
        ) {
            // Act & Assert:
            val sign = engineMath.evalSign(testData.input.operand)
            sign.value shouldBe testData.expected.value
        }
    }
}