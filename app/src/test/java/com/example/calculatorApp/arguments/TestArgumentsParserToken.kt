package com.example.calculatorApp.arguments

import com.example.calculatorApp.TestData.TestDataParserToken
import com.example.calculatorApp.domain.ast.OperatorBinary
import com.example.calculatorApp.domain.ast.Token
import com.example.calculatorApp.utils.OperatorList.operatorBinary

object TestArgumentsParserToken {

    fun provideDataTestParserToken(
        limit: Int = 2,
        binary: Array<OperatorBinary> = operatorBinary,
    ): Sequence<TestDataParserToken> {

        return (-limit..limit).asSequence()
            .zip(binary.asSequence()) { int, operatorBinary ->
                TestDataParserToken(
                    firstOperator = Token.Binary(operatorBinary),
                    secondOperator = Token.Binary(OperatorBinary.Multiplication),
                    number = Token.Number(int.toDouble()),
                )
            }
    }
}