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
    @IgnoredOnParcel val expression: List<Token> = emptyList(),
    val lastOperand: String = SymbolButton.ZERO.label,
    val lastResult: String? = null,
    @IgnoredOnParcel val lastOperator: Operator? = null,
    val cachedOperand: String? = null,
    @IgnoredOnParcel val activeButton: Button? = null,
    val isComputed: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null,
) : Parcelable {
    fun modifyWith(
        vararg transformations: Pair<() -> Boolean, CalculatorStateDomain.() -> CalculatorStateDomain>,
        errorMessage: String? = null,
    ): CalculatorStateDomain {
        return try {
            transformations
                .firstOrNull { it.first() }
                ?.second?.invoke(this)
                ?: this
        } catch (e: Exception) {
            this.copy(hasError = true, errorMessage = errorMessage ?: e.message)
        }
    }
}