package com.example.calculatorApp.model.state

import android.os.Parcelable
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize

@Parcelize
data class CalculatorStateDomain(
    @IgnoredOnParcel override val expression: List<Token> = emptyList(),
    override val lastOperand: String = SymbolButton.ZERO.label,
    @IgnoredOnParcel override val activeButton: Button? = null,

    @IgnoredOnParcel override val lastOperator: Operator? = null,

    override val lastResult: String? = null,
    override val cachedOperand: String? = null,
    override val isComputed: Boolean = false,

    override val hasError: Boolean = false,
    override val errorMessage: String? = null,
) : Parcelable, HasState {

    fun modifyWith(
        vararg transformations: Pair<() -> Boolean, CalculatorStateDomain.() -> CalculatorStateDomain>,
        errorMessage: String? = null,
    ): CalculatorStateDomain =
        runCatching {
            transformations.firstOrNull { it.first() }
                ?.second(this)
                ?: this
        }.getOrElse { e -> this.copy(hasError = true, errorMessage = errorMessage ?: e.message) }
}