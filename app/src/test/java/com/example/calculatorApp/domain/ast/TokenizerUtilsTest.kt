package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.TestData.TestDataTokenizerStandard
import com.example.calculatorApp.TestData.TestDataTokenizerUtils
import com.example.calculatorApp.arguments.TestArgumentsTokenizerStandard
import com.example.calculatorApp.arguments.TestArgumentsTokenizerUtils
import com.example.calculatorApp.domain.ast.TokenizerUtils.isBinary
import com.example.calculatorApp.domain.ast.TokenizerUtils.isNumber
import com.example.calculatorApp.domain.ast.TokenizerUtils.isParenthesis
import com.example.calculatorApp.domain.ast.TokenizerUtils.isUnaryPrefix
import com.example.calculatorApp.domain.ast.TokenizerUtils.isUnarySuffix
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorParenthesis
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Nested

import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream


class TokenizerUtilsTest {

    @Nested
    inner class IsNumber {

        // Arrange:
        private fun provideArgumentsNumbers(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorNumber::class).asStream()
        }

        // Arrange:
        private fun provideArgumentsBinary(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorBinary::class).asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `isNumber should return true for valid numbers`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val number = dataTest.expression.first()
            // Act & Assert:
            number.isNumber() shouldBe true
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsBinary")
        fun `isNumber should return false for non-numeric strings`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val nonNumber = dataTest.expression.first()
            // Act & Assert:
            nonNumber.isNumber() shouldBe false
        }
    }
    @Nested
    inner class IsBinary {

        // Arrange:
        private fun provideArgumentsNumbers(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorNumber::class).asStream()
        }

        // Arrange:
        private fun provideArgumentsBinary(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorBinary::class).asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsBinary")
        fun `isBinary should return true for valid binary operators`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val binary = dataTest.expression.first()
            // Act & Assert:
            binary.isBinary() shouldBe true
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `isBinary should return false for invalid operators`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val nonBinary = dataTest.expression.first()
            // Act & Assert:
            nonBinary.isBinary() shouldBe false
        }
    }
    @Nested
    inner class IsUnaryPrefix {

        @ParameterizedTest
        @CsvSource(
            "±"
        )
        fun `isUnaryPrefix should return true for valid unary prefix operators`(
            dataTest: String
        ) {
            // Arrange & Act & Assert:
            dataTest.isUnaryPrefix() shouldBe true
        }

        @ParameterizedTest
        @CsvSource(
            "¬"
        )
        fun `isUnaryPrefix should return false for invalid operators`(
            dataTest: String
        ) {
            // Arrange & Act & Assert:
            dataTest.isUnaryPrefix() shouldBe false
        }
    }

    @Nested
    inner class IsUnarySuffix {

        @ParameterizedTest
        @CsvSource(
            "%"
        )
        fun `isUnarySuffix should return true for valid unary suffix operators`(
            dataTest: String
        ) {
            // Arrange & Act & Assert:
            dataTest.isUnarySuffix() shouldBe true
        }

        @ParameterizedTest
        @CsvSource(
            "¬"
        )
        fun `isUnarySuffix should return false for invalid operators`(
            dataTest: String
        ) {
            // Arrange & Act & Assert:
            dataTest.isUnarySuffix() shouldBe false
        }
    }

    @Nested
    inner class IsParenthesis {

        // Arrange:
        private fun provideArgumentsNumbers(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorNumber::class).asStream()
        }

        // Arrange:
        private fun provideArgumentsParenthesis(): Stream<TestDataTokenizerStandard> {
            return TestArgumentsTokenizerStandard
                .provide(ButtonCalculatorParenthesis::class).asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsParenthesis")
        fun `isParenthesis should return true for valid parentheses`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val number = dataTest.expression.first()
            // Act & Assert:
            number.isParenthesis() shouldBe true
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `isParenthesis should return false for valid parentheses`(
            dataTest: TestDataTokenizerStandard
        ) {
            // Arrange:
            val number = dataTest.expression.first()
            // Act & Assert:
            number.isParenthesis() shouldBe false
        }
    }

    @Nested
    inner class ToBinaryOperator {

        // Arrange:
        private fun provideArguments(): Stream<TestDataTokenizerUtils> {
            return TestArgumentsTokenizerUtils.provide(ButtonCalculatorBinary::class).asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `toBinaryOperator should correctly convert ButtonCalculatorBinary to OperatorBinary`(
            testData: TestDataTokenizerUtils
        ) {
            // Arrange & Act:
            val button = testData.button as ButtonCalculatorBinary
            val operator = button.toBinaryOperator()
            // Assert:
            testData.expectedOperatorBinary() shouldBe operator
        }

        @Test
        fun `toBinaryOperator should throw exception for unknown ButtonCalculatorBinary`() {
            // Arrange :
            val unknownButton = mockk<Button>(relaxed = true)
            every { unknownButton.symbol.label } returns "ˆß®å´¬"

            // & Act & Assert:
            unknownButton.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    it.symbol.label.toBinaryOperator()
                }

                exception.message shouldBe "Unknown binary operator: ${it.symbol.label}."
            }
        }
    }

    @Nested
    inner class ToUnaryOperator {

        // Arrange:
        private fun provideArguments(): Stream<TestDataTokenizerUtils> {
            return TestArgumentsTokenizerUtils.provide(ButtonCalculatorUnary::class).asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `toUnaryOperator should correctly convert ButtonCalculatorUnary to OperatorUnary`(
            testData: TestDataTokenizerUtils
        ) {
            // Arrange & Act:
            val button = testData.button as ButtonCalculatorUnary
            val operator = button.toUnaryOperator()
            // Assert:
            testData.expectedOperatorUnary() shouldBe operator
        }

        @Test
        fun `toUnaryOperator should throw exception for unknown ButtonCalculatorUnary`() {
            // Arrange :
            val unknownButton = mockk<Button>(relaxed = true)
            every { unknownButton.symbol.label } returns "ˆß®å´¬"

            // & Act & Assert:
            unknownButton.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    it.symbol.label.toUnaryOperator()
                }

                exception.message shouldBe "Unknown unary operator: ${it.symbol.label}."
            }
        }
    }

    @Nested
    inner class ToParenthesisOperator {

        // Arrange:
        private fun provideArguments(): Stream<TestDataTokenizerUtils> {
            return TestArgumentsTokenizerUtils.provide(ButtonCalculatorParenthesis::class).asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `toParenthesisOperator should correctly convert ButtonCalculatorParenthesis to OperatorParenthesis`(
            testData: TestDataTokenizerUtils
        ) {
            // Arrange & Act:
            val button = testData.button as ButtonCalculatorParenthesis
            val operator = button.toParenthesisOperator()
            // Assert:
            testData.expectedOperatorParenthesis() shouldBe operator
        }

        @Test
        fun `toParenthesisOperator should throw exception for unknown ButtonCalculatorParenthesis`() {
            // Arrange :
            val unknownButton = mockk<Button>(relaxed = true)
            every { unknownButton.symbol.label } returns "ˆß®å´¬"

            // & Act & Assert:
            unknownButton.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    it.symbol.label.toParenthesisOperator()
                }

                exception.message shouldBe "Unknown parenthesis operator: ${it.symbol.label}."
            }
        }
    }
}