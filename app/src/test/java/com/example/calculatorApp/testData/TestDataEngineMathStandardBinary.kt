package com.example.calculatorApp.testData

import com.example.calculatorApp.domain.ast.BinaryOperation
import com.example.calculatorApp.domain.ast.BinaryOperations.Add
import com.example.calculatorApp.domain.ast.BinaryOperations.Div
import com.example.calculatorApp.domain.ast.BinaryOperations.Mul
import com.example.calculatorApp.domain.ast.BinaryOperations.Sub
import com.example.calculatorApp.testData.expected.ExpectedEngineMath
import com.example.calculatorApp.testData.expected.ExpectedEngineMathEval
import com.example.calculatorApp.testData.expected.ExpectedEngineMathResult
import com.example.calculatorApp.testData.input.InputEngineMath
import com.example.calculatorApp.testData.input.InputEval

object TestDataEngineMathStandardBinary {

    fun binaryOperationsTest(): Sequence<BinaryOperation> = sequenceOf(Add, Sub, Mul, Div)
    val addTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() + r.value.toDouble()) }
    val subTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() - r.value.toDouble()) }
    val mulTest = ExpectedEngineMathEval { l, r -> ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() * r.value.toDouble()) }

    val divTest = ExpectedEngineMathEval { l, r ->
        val denominator = r.value.toDouble()
        require(denominator != 0.0) { "Division by zero" }
        ExpectedEngineMathResult.normalizeResultTest(l.value.toDouble() / denominator)
    }

    fun engineMathBinaryInput(
        operand: Number,
        previousNumber: Number,
        operation: BinaryOperation,
    ): InputEngineMath.Binary = InputEngineMath.Binary(
        object : InputEval.Binary {
            override val leftOperand: Double = operand.toDouble()
            override val rightOperand: Double = previousNumber.toDouble()
            override val operation: BinaryOperation = operation
        }
    )

    fun engineMathBinaryExpected(
        operand: Number,
        previousNumber: Number,
        operation: BinaryOperation,
    ): ExpectedEngineMath.Binary {
        val eval = operation.toTestEval()

        val delegate = eval.applyTest(
            ExpectedEngineMathResult.normalizeResultTest(operand.toDouble()),
            ExpectedEngineMathResult.normalizeResultTest(previousNumber.toDouble())
        ) as ExpectedEngineMathResult

        return ExpectedEngineMath.Binary(delegate)
    }

    private val operationTestMap: Map<BinaryOperation, ExpectedEngineMathEval> = mapOf(
        Add to addTest, Sub to subTest,
        Mul to mulTest, Div to divTest,
    )

    private fun BinaryOperation.toTestEval(
        mapping: Map<BinaryOperation, ExpectedEngineMathEval> = operationTestMap,
    ): ExpectedEngineMathEval = mapping[this] ?: error("Unknown operation: $this")

    enum class OperandCategoryTest { NEG_INT, NEG_DEC, ZERO_DOUBLE, ZERO_INT, POS_INT, POS_DEC }

    private val valuesByOperandCategoryTest: Map<OperandCategoryTest, List<Number>> = mapOf(
        OperandCategoryTest.NEG_INT to listOf(-3),
        OperandCategoryTest.NEG_DEC to listOf(-3.5),
        OperandCategoryTest.ZERO_DOUBLE to listOf(0.0),
        OperandCategoryTest.ZERO_INT to listOf(0),
        OperandCategoryTest.POS_INT to listOf(3),
        OperandCategoryTest.POS_DEC to listOf(3.5),
    )

    fun provideOperandsTest(mapping: Map<OperandCategoryTest, List<Number>> = valuesByOperandCategoryTest): Sequence<Pair<Number, Number>> =
        mapping.values.asSequence().flatten().flatMap { left ->
            mapping.values.asSequence().flatten().map { right ->
                        left to right
            }
        }
}