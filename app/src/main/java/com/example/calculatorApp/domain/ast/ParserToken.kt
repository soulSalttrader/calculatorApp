package com.example.calculatorApp.domain.ast

class ParserToken : Parser {

    override fun parse(tokens: List<Token>): ASTNode {
        val output = mutableListOf<ASTNode>()
        val operators = mutableListOf<Token>()

        tokens.forEach { token ->
            when (token) {
                is Token.Number -> output.add(ASTNode.Number(token.value))
                is Token.Binary -> handleBinaryOperator(token, output, operators)
                else -> throw IllegalArgumentException("Invalid token for: $token")
            }
        }

        while (operators.isNotEmpty()) { buildOperatorNode(output, operators.removeLast()) }
        require(output.size == 1) { "Invalid expression" }

        return output.single()
    }

    private fun handleBinaryOperator(token: Token.Binary, output: MutableList<ASTNode>, operators: MutableList<Token>) {
        while (operators.isNotEmpty() &&
            Precedence.fromToken(operators.last()).level >= Precedence.fromToken(token).level
        ) {
            buildOperatorNode(output, operators.removeLast())
        }
        operators.add(token)
    }

    private fun buildOperatorNode(output: MutableList<ASTNode>, operatorToken: Token) {
        when (operatorToken) {
            is Token.Binary -> {
                require(output.size >= 2) { "Malformed expression" }
                val right = output.removeLast()
                val left = output.removeLast()
                output.add(ASTNode.Binary(operatorToken.operator, left, right))
            }

            else -> throw IllegalArgumentException("Unexpected token in operator stack, $operatorToken.")
        }
    }
}