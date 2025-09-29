package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.domain.ast.TokenizerUtils.toOperator
import com.example.calculatorApp.model.elements.button.Button

object TestDataEngineStateStandard {

    fun buildTokensFrom(
        previousOperand: Number,
        button: Button,
    ): List<Token> = listOf(
        Token.Number(previousOperand.toDouble()),
        Token.Binary(button.toOperator() as OperatorBinary)
    )
}