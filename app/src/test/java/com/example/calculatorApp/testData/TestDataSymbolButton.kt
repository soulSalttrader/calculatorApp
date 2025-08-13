package com.example.calculatorApp.testData

import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.model.symbols.SymbolCategory

object TestDataSymbolButton {

    fun symbolsOfCategory(category: SymbolCategory) = SymbolButton.entries.filter { it.category == category }

    val symbolsNumberTest = symbolsOfCategory(SymbolCategory.NUMBER)
    val symbolsBinaryTest = symbolsOfCategory(SymbolCategory.BINARY)
    val symbolsUnaryPrefixTest = symbolsOfCategory(SymbolCategory.UNARY_PREFIX)
    val symbolsUnarySuffixTest = symbolsOfCategory(SymbolCategory.UNARY_SUFFIX)
    val symbolsControlTest = symbolsOfCategory(SymbolCategory.CONTROL)
    val symbolsParenthesisTest = symbolsOfCategory(SymbolCategory.PARENTHESIS)

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