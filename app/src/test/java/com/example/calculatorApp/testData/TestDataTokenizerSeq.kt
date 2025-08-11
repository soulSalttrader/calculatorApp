package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toBinaryOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toParenthesisOperator
import com.example.calculatorApp.domain.ast.TokenizerUtils.toUnaryOperator
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.testData.TestDataElementSeq.buttonsNumbersTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsBinaryTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsParenthesisTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsUnarySuffixTest
import kotlin.reflect.full.isSubclassOf

object TestDataTokenizerSeq {

    private fun <T> Sequence<T>.toSymbolLists(labelProvider: (T) -> String) =
        map { listOf(labelProvider(it)) }

    private inline fun <reified T> Sequence<T>.toSymbolLists(): Sequence<List<String>> =
        when {
            T::class.isSubclassOf(Button::class) -> toSymbolLists { (it as Button).symbol.label }
            T::class.isSubclassOf(Operator::class) -> toSymbolLists { (it as Operator).symbol.label }
            else -> throw IllegalArgumentException("Unsupported type: ${T::class}")
        }

    private fun <T, R : Token> Sequence<T>.toTokens(
        transform: (String) -> R,
        labelProvider: (T) -> String,
    ): Sequence<R> = map { transform(labelProvider(it)) }

    private inline fun <reified T, R : Token> Sequence<T>.toTokens(noinline transform: (String) -> R): Sequence<R> =
        when {
            T::class.isSubclassOf(Button::class) -> toTokens(transform) { (it as Button).symbol.label }
            T::class.isSubclassOf(Operator::class) -> toTokens(transform) { (it as Operator).symbol.label }
            else -> throw IllegalArgumentException("Unsupported type: ${T::class}")
        }

    val seqSymbolsNumberTest = buttonsNumbersTest.toSymbolLists()
    val tokensNumberTest = buttonsNumbersTest.toTokens { Token.Number(it.toDouble()) }

    val seqSymbolsBinaryTest = operatorsBinaryTest.toSymbolLists()
    val tokensBinaryTest = operatorsBinaryTest.toTokens { Token.Binary(it.toBinaryOperator()) }

    val seqSymbolsUnaryPrefixTest = operatorsUnaryPrefixTest.toSymbolLists()
    val tokensUnaryPrefixTest = operatorsUnaryPrefixTest.toTokens { Token.Unary(it.toUnaryOperator()) }

    val seqSymbolsUnarySuffixTest = operatorsUnarySuffixTest.toSymbolLists()
    val tokensUnarySuffixTest = operatorsUnarySuffixTest.toTokens { Token.Unary(it.toUnaryOperator()) }

    val seqSymbolsParenthesisTest = operatorsParenthesisTest.toSymbolLists()
    val tokensParenthesisTest = operatorsParenthesisTest.toTokens { Token.Parenthesis(it.toParenthesisOperator()) }

    fun seqSymbolsAllTest(
        filter: Sequence<String> = sequenceOf(
            SymbolButton.ALL_CLEAR.label,
            SymbolButton.CLEAR.label,
            SymbolButton.DECIMAL.label,
            SymbolButton.EQUALS.label,
        )
    ): List<String> = SymbolButton.entries
        .asSequence()
        .map { it.label }
        .filter { it !in filter }
        .toList()
}