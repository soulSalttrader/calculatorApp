package com.example.calculatorApp.arguments

import com.example.calculatorApp.TestData.TestDataParserToken
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.utils.OperatorList.operatorBinary

object TestArgumentsParserToken {

    fun provideDataTestParserToken(
        limit: Int = 2,
        binary: Sequence<Operator> = operatorBinary,
    ): Sequence<TestDataParserToken> {
        return (-limit..limit).asSequence()
            .zip(binary) { int, operatorBinary ->
                TestDataParserToken(
                    firstOperator = Token.Binary(operatorBinary as OperatorBinary),
                    secondOperator = Token.Binary(OperatorBinary.Multiplication),
                    number = Token.Number(int.toDouble()),
                )
            }
    }
}