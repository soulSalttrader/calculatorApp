package com.example.calculatorApp.TestData

import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.symbols.SymbolButton
import com.example.calculatorApp.utils.ButtonCalculatorList

data class TestDataEngineStateStandard(
    val expression: List<Token> = emptyList(),
    val lastOperand: String = SymbolButton.ZERO.label,
    val buttonBinary: Button = ButtonCalculatorBinary.Addition,
    val buttonUnary: Button = ButtonCalculatorUnary.Sign,
    val buttonControls: Sequence<Button> = ButtonCalculatorList.controls,
    val buttonNumber: Button = ButtonCalculatorNumber.Three,
) {
    fun expectedSign(): Map<String, String> = mapOf(
            "-2.5" to "2.5",
            "-1" to "1",
            "-1.0" to "1",
            "0.0" to "0",
            "1" to "-1",
            "1.0" to "-1",
            "2.5" to "-2.5",
            "NaN" to "NaN",
    )

    fun expectedPercentage(): Map<Pair<String, ButtonCalculatorBinary>, String> = listOf(
            "-2.5" to ("0.125" to "-0.025"),
            "-1" to ("0.02" to "-0.01"),
            "-1.0" to ("0.02" to "-0.01"),
            "0.0" to ("0" to "0"),
            "1" to ("0.02" to "0.01"),
            "1.0" to ("0.02" to "0.01"),
            "2.5" to ("0.125" to "0.025"),
            "NaN" to ("NaN" to "NaN"),
        ).flatMap { (value, results) ->
            listOf(
                (value to ButtonCalculatorBinary.Addition) to results.first,
                (value to ButtonCalculatorBinary.Multiplication) to results.second,
            )
        }.toMap()

    fun expectedPercentageWithEquals(): Map<Pair<String, ButtonCalculatorBinary>, String> = listOf(
        "-2.5" to ("-2.6875" to "-33.333333333333336"),
        "-1" to ("-1.09" to "-11.11111111111111"),
        "-1.0" to ("-1.09" to "-11.11111111111111"),
        "0.0" to ("0.0" to "0.0"),
        "1" to ("1.11" to "9.090909090909092"),
        "1.0" to ("1.11" to "9.090909090909092"),
        "2.5" to ("2.8125" to "20.0"),
        "NaN" to ("null" to "null"),
    ).flatMap { (value, results) ->
        listOf(
            (value to ButtonCalculatorBinary.Addition) to results.first,
            (value to ButtonCalculatorBinary.Division) to results.second,
        )
    }.toMap()

    fun expectedNumber(): Map<String, String> = mapOf(
        "-2.5" to "-2.53",
        "-1" to "-13",
        "-1.0" to "-1.03",
        "0.0" to "0.03",
        "1" to "13",
        "1.0" to "1.03",
        "2.5" to "2.53",
        "NaN" to "NaN",
    )
}