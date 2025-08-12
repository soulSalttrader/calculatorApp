package com.example.calculatorApp.domain.ast

import com.example.calculatorApp.arguments.TestArgumentsTokenizer.provideTokenizerTestCases
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsNumbersTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsBinaryTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsParenthesisTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsUnarySuffixTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.expectedMixed
import com.example.calculatorApp.testData.TestDataTokenizerSeq.inputMixed
import com.example.calculatorApp.testData.expected.Expected
import com.example.calculatorApp.testData.expected.ExpectedToken
import com.example.calculatorApp.testData.input.Input
import com.example.calculatorApp.testData.input.InputToken
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

class TokenizerStandardTest {

    @Nested
    inner class Tokenize {

        private lateinit var tokenizer: Tokenizer

        // Arrange:
        fun provideArgumentsNumbers(): Stream<TestCase<Input, Expected>> =
            provideTokenizerTestCases(buttonsNumbersTest).asStream()

        fun provideArgumentsBinary(): Stream<TestCase<Input, Expected>> =
            provideTokenizerTestCases(operatorsBinaryTest).asStream()

        fun provideArgumentsUnaryPrefix(): Stream<TestCase<Input, Expected>> =
            provideTokenizerTestCases(operatorsUnaryPrefixTest).asStream()

        fun provideArgumentsUnarySuffix(): Stream<TestCase<Input, Expected>> =
            provideTokenizerTestCases(operatorsUnarySuffixTest).asStream()

        fun provideArgumentsParenthesis(): Stream<TestCase<Input, Expected>> =
            provideTokenizerTestCases(operatorsParenthesisTest).asStream()

        @BeforeEach
        fun setUp() {
            tokenizer = TokenizerStandard()
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `tokenize single number`(
            dataTest: TestCase<InputToken, ExpectedToken>
        ) {
            // Arrange:
            val number = dataTest.input.source.map { it.symbol.label }
            // Act:
            val tokenized = tokenizer.tokenize(number)
            // Assert:
            tokenized shouldBe (dataTest.expected).tokens
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsBinary")
        fun `tokenize simple binary operator`(
            dataTest: TestCase<InputToken, ExpectedToken>
        ) {
            // Arrange:
            val binary = dataTest.input.source.map { it.symbol.label }
            // Act:
            val tokenized = tokenizer.tokenize(binary)
            // Assert:
            tokenized shouldBe (dataTest.expected).tokens
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsUnaryPrefix")
        fun `tokenize prefix unary operators`(
            dataTest: TestCase<InputToken, ExpectedToken>
        ) {
            // Arrange:
            val unary = dataTest.input.source.map { it.symbol.label }
            // Act:
            val tokenized = tokenizer.tokenize(unary)
            // Assert:
            tokenized shouldBe (dataTest.expected).tokens
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsUnarySuffix")
        fun `tokenize suffix unary operators`(
            dataTest: TestCase<InputToken, ExpectedToken>
        ) {
            // Arrange:
            val unary = dataTest.input.source.map { it.symbol.label }
            // Act:
            val tokenized = tokenizer.tokenize(unary)
            // Assert:
            tokenized shouldBe (dataTest.expected).tokens
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsParenthesis")
        fun `tokenize parentheses`(
            dataTest: TestCase<InputToken, ExpectedToken>
        ) {
            // Arrange:
            val parenthesis = dataTest.input.source.map { it.symbol.label }
            // Act:
            val tokenized = tokenizer.tokenize(parenthesis)
            // Assert:
            tokenized shouldBe (dataTest.expected).tokens
        }

        @Test
        fun `tokenize multiple mixed tokens`() {
            // Arrange:
            val mixed = inputMixed
            // & Act:
            val tokenized = tokenizer.tokenize(mixed)
            // Assert:
            tokenized shouldContainExactlyInAnyOrder expectedMixed
        }

        @ParameterizedTest
        @CsvSource(
            "´",
            "¬",
            "ˆ",
            "ß",
            "˚"
        )
        fun `tokenize invalid token should throw exception`(
            dataTest: String
        ) {
            // Arrange:
            val invalidInput = listOf(dataTest)
            // Act & Assert:
            invalidInput.let {
                val exception = shouldThrow<IllegalArgumentException> {
                    tokenizer.tokenize(invalidInput)
                }

                exception.message shouldBe "Invalid token: ${it.joinToString()}"
            }
        }
    }
}