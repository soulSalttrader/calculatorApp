package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.OperatorParenthesis
import com.example.calculatorApp.domain.ast.OperatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.model.symbols.SymbolCategory
import com.example.calculatorApp.testData.TestDataElement.operatorsBinaryTest
import com.example.calculatorApp.testData.TestDataElement.operatorsParenthesisTest
import com.example.calculatorApp.testData.TestDataElement.operatorsUnaryPrefixTest
import com.example.calculatorApp.testData.TestDataElement.operatorsUnarySuffixTest

object TestDataSymbolButton {

    fun symbolsOfCategory(category: SymbolCategory) = SymbolButton.entries.filter { it.category == category }

    val symbolsNumberTest = symbolsOfCategory(SymbolCategory.NUMBER)
    val symbolsBinaryTest = symbolsOfCategory(SymbolCategory.BINARY)
    val symbolsUnaryPrefixTest = symbolsOfCategory(SymbolCategory.UNARY_PREFIX)
    val symbolsUnarySuffixTest = symbolsOfCategory(SymbolCategory.UNARY_SUFFIX)
    val symbolsControlTest = symbolsOfCategory(SymbolCategory.CONTROL)
    val symbolsParenthesisTest = symbolsOfCategory(SymbolCategory.PARENTHESIS)


    val symbolOperatorMapReference: Map<String, Operator> = buildMap {
        put(SymbolButton.ADDITION.label, OperatorBinary.Addition)
        put(SymbolButton.SUBTRACTION.label, OperatorBinary.Subtraction)
        put(SymbolButton.DIVISION.label, OperatorBinary.Division)
        put(SymbolButton.MULTIPLICATION.label, OperatorBinary.Multiplication)
        put(SymbolButton.SIGN.label, OperatorUnary.Prefix.Sign)
        put(SymbolButton.PERCENTAGE.label, OperatorUnary.Suffix.Percentage)
        put(SymbolButton.OPEN_PARENT.label, OperatorParenthesis.Open)
        put(SymbolButton.CLOSE_PARENT.label, OperatorParenthesis.Close)
    }

    /**
     * Dynamically generated mapping between [SymbolButton] labels and their corresponding [Operator]s.
     *
     * This map is built from the runtime definitions of operators (binary, unary, parenthesis, etc.),
     * ensuring it always stays in sync with the sealed operator classes.
     *
     * Example output:
     *
     * ```
     * {
     *   "+" = Addition,
     *   "-" = Subtraction,
     *   "÷" = Division,
     *   "×" = Multiplication,
     *   "±" = Sign,
     *   "%" = Percentage,
     *   "(" = Open,
     *   ")" = Close
     * }
     * ```
     *
     * Note: Values are nullable ([Operator?]) because if a [SymbolButton] does not have a matching
     * operator in the sequence, the map entry will contain `null`.
     */
    val symbolOperatorsMapGenerated: Map<String, Operator?> = buildMap {
        fun List<SymbolButton>.putOperatorsFrom(operators: Sequence<Operator>) {
            val operatorMap = operators.associateBy { it.symbol.label }
            this.forEach { put(it.label, operatorMap[it.label]) }
        }

        listOf(
            symbolsBinaryTest to operatorsBinaryTest,
            symbolsUnaryPrefixTest to operatorsUnaryPrefixTest,
            symbolsUnarySuffixTest to operatorsUnarySuffixTest,
            symbolsParenthesisTest to operatorsParenthesisTest,
        ).forEach { (symbols, operators) ->
            symbols.putOperatorsFrom(operators)
        }
    }

    /**
     * Returns an anonymous function that generates a list of strings from [SymbolButton] entries
     * filtered by the provided [category] and [filter] function.
     *
     * Example usage:
     * ```
     * val labelsNumberTest = symbolsOfCategoryAnonymous(SymbolCategory.NUMBER) { actual, target -> actual == target }() { it.label }
     * ```
     *
     * @param category the target category to filter [SymbolButton] entries.
     * @param filter a function that receives the actual and target [SymbolCategory] and returns whether to include the entry.
     * @return an anonymous function that takes a mapping function from [SymbolButton] to [String] and returns a list of mapped strings.
     */
    fun symbolsOfCategoryAnonymous(
        category: SymbolCategory,
        filter: (SymbolCategory, SymbolCategory) -> Boolean,
    ) = fun(map: (SymbolButton) -> String): List<String> =
        SymbolButton.entries
            .filter { filter(it.category, category) }
            .map(map)

    val labelsNumberTest = symbolsOfCategoryAnonymous(SymbolCategory.NUMBER) { actual, target -> actual == target }() { it.label }
    val labelsBinaryTest = symbolsOfCategoryAnonymous(SymbolCategory.BINARY) { actual, target -> actual == target }() { it.label }
    val labelsUnaryPrefixTest = symbolsOfCategoryAnonymous(SymbolCategory.UNARY_PREFIX) { actual, target -> actual == target }() { it.label }
    val labelsUnarySuffixTest = symbolsOfCategoryAnonymous(SymbolCategory.UNARY_SUFFIX) { actual, target -> actual == target }() { it.label }
    val labelsControlTest = symbolsOfCategoryAnonymous(SymbolCategory.CONTROL) { actual, target -> actual == target }() { it.label }
    val labelsParenthesisTest = symbolsOfCategoryAnonymous(SymbolCategory.PARENTHESIS) { actual, target -> actual == target }() { it.label }

    /**
     * Returns a lambda function that generates a list of strings from [SymbolButton] entries
     * filtered by the provided [category] and [filter] function.
     *
     * Example usage:
     * ```
     * val labelsNumberTest = symbolsOfCategoryLambda(SymbolCategory.NUMBER) { actual, target -> actual == target }() { it.label }
     * ```
     *
     * @param category the target category to filter [SymbolButton] entries.
     * @param filter a function that receives the actual and target [SymbolCategory] and returns whether to include the entry.
     * @return a lambda that takes a mapping function from [SymbolButton] to [String] and returns a list of mapped strings.
     */
    fun symbolsOfCategoryLambda(
        category: SymbolCategory,
        filter: (SymbolCategory, SymbolCategory) -> Boolean,
    ): ((SymbolButton) -> String) -> List<String> =
        { map -> SymbolButton.entries
            .filter { filter(it.category, category) }
            .map(map)
        }
}