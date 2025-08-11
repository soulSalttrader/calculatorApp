package com.example.calculatorApp.domain.ast


import com.example.calculatorApp.arguments.TestArgumentsTokenizer
import com.example.calculatorApp.arguments.TestArgumentsTokenizer.provideTokenizerTestCases
import com.example.calculatorApp.testData.TestCase
import com.example.calculatorApp.testData.TestDataTokenizerSeq.seqSymbolsAllTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.seqSymbolsBinaryTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.seqSymbolsNumberTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.seqSymbolsParenthesisTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.seqSymbolsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataTokenizerSeq.seqSymbolsUnarySuffixTest
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

typealias Expression = List<String>

class TokenizerStandardTest {

    @Nested
    inner class Tokenize {

        private lateinit var tokenizer: Tokenizer

        // Arrange:
        fun provideArgumentsNumbers(): Stream<TestCase<List<String>, List<Token>>> =
            provideTokenizerTestCases(seqSymbolsNumberTest).asStream()

        // Arrange:
        fun provideArgumentsBinary(): Stream<TestCase<List<String>, List<Token>>> =
            provideTokenizerTestCases(seqSymbolsBinaryTest).asStream()

        // Arrange:
        fun provideArgumentsParenthesis(): Stream<TestCase<List<String>, List<Token>>> =
            provideTokenizerTestCases(seqSymbolsParenthesisTest).asStream()

        // Arrange:
        fun provideArgumentsUnaryPrefix(): Stream<TestCase<List<String>, List<Token>>> =
            provideTokenizerTestCases(seqSymbolsUnaryPrefixTest).asStream()

        // Arrange:
        fun provideArgumentsUnarySuffix(): Stream<TestCase<List<String>, List<Token>>> =
            provideTokenizerTestCases(seqSymbolsUnarySuffixTest).asStream()

        @BeforeEach
        fun setUp() {
            tokenizer = TokenizerStandard()
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsNumbers")
        fun `tokenize single number`(
            dataTest: TestCase<List<String>, List<Token>>
        ) {
            // Arrange:
            val number = dataTest.input
            // Act:
            val tokenized = tokenizer.tokenize(number)
            // Assert:
            tokenized shouldBe dataTest.expected
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsBinary")
        fun `tokenize simple binary operator`(
            dataTest: TestCase<List<String>, List<Token>>
        ) {
            // Arrange:
            val binary = dataTest.input
            // Act:
            val tokenized = tokenizer.tokenize(binary)
            // Assert:
            tokenized shouldBe dataTest.expected
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsUnaryPrefix")
        fun `tokenize prefix unary operators`(
            dataTest: TestCase<List<String>, List<Token>>
        ) {
            // Arrange:
            val unary = dataTest.input
            // Act:
            val tokenized = tokenizer.tokenize(unary)
            // Assert:
            tokenized shouldBe dataTest.expected
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsUnarySuffix")
        fun `tokenize suffix unary operators`(
            dataTest: TestCase<List<String>, List<Token>>
        ) {
            // Arrange:
            val unary = dataTest.input
            // Act:
            val tokenized = tokenizer.tokenize(unary)
            // Assert:
            tokenized shouldBe dataTest.expected
        }

        @ParameterizedTest
        @MethodSource("provideArgumentsParenthesis")
        fun `tokenize parentheses`(
            dataTest: TestCase<List<String>, List<Token>>
        ) {
            // Arrange:
            val parenthesis = dataTest.input
            // Act:
            val tokenized = tokenizer.tokenize(parenthesis)
            // Assert:
            tokenized shouldBe dataTest.expected
        }

        @Test
        fun `tokenize multiple mixed tokens`() {
            // Arrange & Act:
            val tokenized = tokenizer.tokenize(seqSymbolsAllTest())
            // Assert:
            tokenized shouldContainExactlyInAnyOrder TestArgumentsTokenizer.expectedMixed()
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