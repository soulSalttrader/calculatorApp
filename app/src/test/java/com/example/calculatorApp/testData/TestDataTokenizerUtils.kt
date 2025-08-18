package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.OperatorParenthesis
import com.example.calculatorApp.domain.ast.OperatorUnary
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.symbols.SymbolButton

//data class TestDataTokenizerUtils(val button: Button) {
//
//    fun expectedOperatorBinary(): OperatorBinary {
//        return when (button.symbol.label) {
//            SymbolButton.ADDITION.label -> OperatorBinary.Addition
//            SymbolButton.SUBTRACTION.label -> OperatorBinary.Subtraction
//            SymbolButton.DIVISION.label -> OperatorBinary.Division
//            SymbolButton.MULTIPLICATION.label -> OperatorBinary.Multiplication
//
//            else -> throw  IllegalArgumentException("Expected OperatorBinary, but was: ${button.symbol.label}.")
//        }
//    }
//
//    fun expectedOperatorUnary(): OperatorUnary {
//        return when (button.symbol.label) {
//            SymbolButton.SIGN.label -> OperatorUnary.Prefix.Sign
//            SymbolButton.PERCENTAGE.label -> OperatorUnary.Suffix.Percentage
//
//            else -> throw  IllegalArgumentException("Expected OperatorUnary, but was: ${button.symbol.label}.")
//        }
//    }
//
//    fun expectedOperatorParenthesis(): OperatorParenthesis {
//        return when (button.symbol.label) {
//            SymbolButton.OPEN_PARENT.label -> OperatorParenthesis.Open
//            SymbolButton.CLOSE_PARENT.label -> OperatorParenthesis.Close
//
//            else -> throw  IllegalArgumentException("Expected OperatorParenthesis, but was: ${button.symbol.label}.")
//        }
//    }
//}