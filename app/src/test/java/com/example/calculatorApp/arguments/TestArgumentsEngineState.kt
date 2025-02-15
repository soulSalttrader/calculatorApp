package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream
import kotlin.math.pow

object TestArgumentsEngineState {

    fun provideArithmeticArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorArithmetic.Division, 2.0, 0.5),
            Arguments.of(2.0, ButtonCalculatorArithmetic.Division, 0.0, Double.NaN),
            Arguments.of(1.0, ButtonCalculatorArithmetic.Multiplication, 2.0, 2.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Multiplication, 1.0, 0.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Subtraction, -1.0, 1.0),
            Arguments.of(3.5, ButtonCalculatorArithmetic.Subtraction, 1.5, 2.0),
            Arguments.of(100.0, ButtonCalculatorArithmetic.Addition, 200.0, 300.0),
            Arguments.of(100000.0, ButtonCalculatorArithmetic.Addition, 200000.0, 300000.0),
        )
    }

    fun provideRepeatableEqualsArguments(): Stream<Arguments> {
        return Stream.of(
            // operandLeft | operation | operandRight | expected
            Arguments.of(100.0, ButtonCalculatorArithmetic.Addition, 0.0, 100.0),
            Arguments.of(50.0, ButtonCalculatorArithmetic.Subtraction, 0.0, 50.0),
            Arguments.of(42.0, ButtonCalculatorArithmetic.Multiplication, 1.0, 42.0),
            Arguments.of(99.9, ButtonCalculatorArithmetic.Division, 1.0, 99.9),

            Arguments.of(0.0, ButtonCalculatorArithmetic.Multiplication, 5.0, 0.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Division, 7.0, 0.0),

            Arguments.of(-5.0, ButtonCalculatorArithmetic.Addition, -5.0, -5.0 + (-5.0 * 10)),
            Arguments.of(-10.0, ButtonCalculatorArithmetic.Subtraction, -2.0, -10.0 - (-2.0 * 10)),
            Arguments.of(-3.0, ButtonCalculatorArithmetic.Multiplication, -3.0, -3.0 * (-3.0).pow(10.0)),
            Arguments.of(-6.0, ButtonCalculatorArithmetic.Division, -2.0, -6.0 / (-2.0).pow(10.0)),

            Arguments.of(Double.MAX_VALUE, ButtonCalculatorArithmetic.Division, 2.0, Double.MAX_VALUE / 2.0.pow(10.0)),
            Arguments.of(Double.MIN_VALUE, ButtonCalculatorArithmetic.Multiplication, 2.0, Double.MIN_VALUE * 2.0.pow(10.0)),

            Arguments.of(10.0, ButtonCalculatorArithmetic.Division, 3.0, 10.0 / 3.0.pow(10.0)),
            Arguments.of(2.5, ButtonCalculatorArithmetic.Multiplication, 2.0, 2.5 * 2.0.pow(10.0))
        )
    }
}