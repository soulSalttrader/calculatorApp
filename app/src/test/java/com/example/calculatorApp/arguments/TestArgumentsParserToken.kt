package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataParserToken
import com.example.calculatorApp.domain.ast.Operator
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.testData.TestDataElementSeq.operatorsBinaryTest

object TestArgumentsParserToken {

    fun provideDataTestParserToken(
        limit: Int = 2,
        binary: Sequence<Operator> = operatorsBinaryTest,
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