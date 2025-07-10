package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.ASTNode
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token

data class TestDataParserToken(
    val firstOperator: Token.Binary,
    val secondOperator: Token.Binary,
    val number: Token.Number,
) {
    // Expected for a single number test
    fun expectedSingleNumber(): ASTNode = ASTNode.Number(number.value)

    // Expected for a simple binary expression test
    fun expectedSimpleBinaryExpression(): ASTNode =
        ASTNode.Binary(
            operator = firstOperator.operator,
            left = ASTNode.Number(number.value),
            right = ASTNode.Number(number.value),
        )

    // Expected for an expression with multiple operators
    fun expectedMultipleOperators(): ASTNode =
        ASTNode.Binary(
            operator = firstOperator.operator,
            left = ASTNode.Binary(
                firstOperator.operator,
                ASTNode.Number(number.value),
                ASTNode.Number(number.value),
            ),
            right = ASTNode.Number(number.value),
        )

    // Expected for expressions with different precedence
    // A low-precedence operator (+, -) appears first → Higher precedence (*, /) goes to right.
    // A high-precedence operator (*, /) appears first → The next operation has to go to left.
    fun expectedDifferentPrecedence(): ASTNode {
        val numberNode = ASTNode.Number(number.value)
        return when (firstOperator.operator) {
            is OperatorBinary.Addition, OperatorBinary.Subtraction ->
                ASTNode.Binary(
                    operator = firstOperator.operator,
                    left = numberNode,
                    right = ASTNode.Binary(
                        secondOperator.operator,
                        numberNode,
                        numberNode
                    ),
                )
            is OperatorBinary.Multiplication, OperatorBinary.Division ->
                ASTNode.Binary(
                    operator = secondOperator.operator,
                    left = ASTNode.Binary(
                        firstOperator.operator,
                        numberNode,
                        numberNode
                    ),
                    right = numberNode,
                )
        }
    }
}