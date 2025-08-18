package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.arguments.TestArgumentsTokenizerUtils.provideTokenUtilsTestCasesLabels
import com.example.calculatorApp.arguments.TestArgumentsTokenizerUtils.provideTokenUtilsTestCasesOperators
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
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataSymbolButton.symbolsBinaryTest
import com.example.calculatorApp.testData.TestDataSymbolButton.symbolsNumberTest
import com.example.calculatorApp.testData.TestDataSymbolButton.symbolsParenthesisTest
import com.example.calculatorApp.testData.TestDataSymbolButton.symbolsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataSymbolButton.symbolsUnarySuffixTest
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedTokenUtils
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputTokenUtils
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
    inner class IsBinary {

        // Arrange:
        private fun provideArgumentsBinary(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                OperatorBinary::class,
                symbolsBinaryTest,
            ).asStream()

        private fun provideArgumentsNonBinary(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                ButtonCalculatorParenthesis::class,
                symbolsParenthesisTest,
                false,
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsBinary")
        fun `isBinary should return true for valid binary operators`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val binary = dataTest.input.source.symbol.label
            // Act & Assert:
            binary.isBinary() shouldBe dataTest.expected.matches
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNonBinary")
        fun `isBinary should return false for invalid operators`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val binary = dataTest.input.source.symbol.label
            // Act & Assert:
            binary.isBinary() shouldBe dataTest.expected.matches
        }
    }

    @Nested
    inner class IsUnaryPrefix {

        // Arrange:
        private fun provideArgumentsUnaryPrefix(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                OperatorUnary.Prefix::class,
                symbolsUnaryPrefixTest,
            ).asStream()

        private fun provideArgumentsNonUnaryPrefix(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                ButtonCalculatorParenthesis::class,
                symbolsParenthesisTest,
                false,
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsUnaryPrefix")
        fun `isUnaryPrefix should return true for valid unary prefix operators param`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val unary = dataTest.input.source.symbol.label
            // Act & Assert:
            unary.isUnaryPrefix() shouldBe dataTest.expected.matches
        }

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
        @MethodSource("provideArgumentsNonUnaryPrefix")
        fun `isUnaryPrefix should return false for invalid operators param`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val unary = dataTest.input.source.symbol.label
            // Act & Assert:
            unary.isUnaryPrefix() shouldBe dataTest.expected.matches
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

        // Arrange:
        private fun provideArgumentsUnarySuffix(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                OperatorUnary.Suffix::class,
                symbolsUnarySuffixTest,
            ).asStream()

        private fun provideArgumentsNonUnarySuffix(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                ButtonCalculatorParenthesis::class,
                symbolsParenthesisTest,
                false,
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsUnarySuffix")
        fun `isUnarySuffix should return true for valid unary suffix operators param`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val unary = dataTest.input.source.symbol.label
            // Act & Assert:
            unary.isUnarySuffix() shouldBe dataTest.expected.matches
        }

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
        @MethodSource("provideArgumentsNonUnarySuffix")
        fun `isUnarySuffix should return false for invalid operators param`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val unary = dataTest.input.source.symbol.label
            // Act & Assert:
            unary.isUnarySuffix() shouldBe dataTest.expected.matches
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
    inner class IsNumber {

        // Arrange:
        private fun provideArgumentsNumbers(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                ButtonCalculatorNumber::class,
                symbolsNumberTest,
            ).asStream()

        private fun provideArgumentsNonNumbers(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                ButtonCalculatorParenthesis::class,
                symbolsParenthesisTest,
                false,
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `isNumber should return true for valid numbers`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val number = dataTest.input.source.symbol.label
            // Act & Assert:
            number.isNumber() shouldBe dataTest.expected.matches
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNonNumbers")
        fun `isNumber should return false for non-numeric strings`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val number = dataTest.input.source.symbol.label
            // Act & Assert:
            number.isNumber() shouldBe dataTest.expected.matches
        }
    }

    @Nested
    inner class IsParenthesis {

        private fun provideArgumentsParenthesis(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                OperatorParenthesis::class,
                symbolsParenthesisTest,
            ).asStream()

        private fun provideArgumentsNonParenthesis(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesLabels(
                OperatorUnary.Prefix::class,
                symbolsUnaryPrefixTest,
                false,
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsParenthesis")
        fun `isParenthesis should return true for valid parentheses`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val number = dataTest.input.source.symbol.label
            // Act & Assert:
            number.isParenthesis() shouldBe dataTest.expected.matches
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNonParenthesis")
        fun `isParenthesis should return false for invalid parentheses`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange:
            val number = dataTest.input.source.symbol.label
            // Act & Assert:
            number.isParenthesis() shouldBe dataTest.expected.matches
        }
    }

    @Nested
    inner class ToBinaryOperator {

        // Arrange:
        private fun provideArgumentsBinaryOperator(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesOperators(
                ButtonCalculatorBinary::class
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsBinaryOperator")
        fun `toBinaryOperator should correctly convert ButtonCalculatorBinary to OperatorBinary`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange & Act:
            val button = dataTest.input.source as ButtonCalculatorBinary
            val operator = button.toBinaryOperator()
            // Assert:
            operator shouldBe dataTest.expected.operator
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
        private fun provideArgumentsUnaryOperator(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesOperators(
                ButtonCalculatorUnary::class
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsUnaryOperator")
        fun `toUnaryOperator should correctly convert ButtonCalculatorUnary to OperatorUnary`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange & Act:
            val button = dataTest.input.source as ButtonCalculatorUnary
            val operator = button.toUnaryOperator()
            // Assert:
            operator shouldBe dataTest.expected.operator
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
        private fun provideArgumentsParenthesisOperator(): Stream<TestCase<Input, Expected>> =
            provideTokenUtilsTestCasesOperators(
                ButtonCalculatorParenthesis::class
            ).asStream()

        @ParameterizedTest
        @MethodSource("provideArgumentsParenthesisOperator")
        fun `toParenthesisOperator should correctly convert ButtonCalculatorParenthesis to OperatorParenthesis`(
            dataTest: TestCase<InputTokenUtils, ExpectedTokenUtils>
        ) {
            // Arrange & Act:
            val button = dataTest.input.source as ButtonCalculatorParenthesis
            val operator = button.toParenthesisOperator()
            // Assert:
            operator shouldBe dataTest.expected.operator
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