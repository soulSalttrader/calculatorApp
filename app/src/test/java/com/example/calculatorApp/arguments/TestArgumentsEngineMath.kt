package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestArgumentsEngineMath {

    fun provideSignArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, -1.0),
            Arguments.of(5.5, -5.5),
            Arguments.of(99.0, -99.0),
            Arguments.of(0.329, -0.329),
            Arguments.of(999.999, -999.999),

            Arguments.of(-1.0, 1.0),
            Arguments.of(-5.5, 5.5),
            Arguments.of(-99.0, 99.0),
            Arguments.of(-0.329, 0.329),
            Arguments.of(-999.999, 999.999),
        )
    }

    fun providesPercentArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, 0.01),
            Arguments.of(329.0, 3.29),
            Arguments.of(1000.0, 10.0),

            Arguments.of(-1.0, -0.01),
            Arguments.of(-329.0, -3.29),
            Arguments.of(-1000.0, -10.0),

            Arguments.of(0.01, 0.0001),
            Arguments.of(0.329, 0.00329),
            Arguments.of(0.999, 0.00999),

            Arguments.of(-0.01, -0.0001),
            Arguments.of(-0.329, -0.00329),
            Arguments.of(-0.999, -0.00999),

            Arguments.of(0.0, 0.0),
        )
    }

    fun provideAdditionArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorArithmetic.Addition, 2.0, 3.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Addition, 1.0, 1.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Addition, -1.0, -1.0),
            Arguments.of(3.5, ButtonCalculatorArithmetic.Addition, 4.4, 7.9),
            Arguments.of(100.0, ButtonCalculatorArithmetic.Addition, 200.0, 300.0),
            Arguments.of(100000.0, ButtonCalculatorArithmetic.Addition, 200000.0, 300000.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorArithmetic.Addition, Double.MAX_VALUE, Double.MAX_VALUE + Double.MAX_VALUE),
            Arguments.of(Double.MIN_VALUE, ButtonCalculatorArithmetic.Addition, Double.MIN_VALUE, Double.MIN_VALUE + Double.MIN_VALUE),
        )
    }

    fun provideSubtractionArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorArithmetic.Subtraction, 2.0, -1.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Subtraction, 1.0, -1.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Subtraction, -1.0, 1.0),
            Arguments.of(3.5, ButtonCalculatorArithmetic.Subtraction, 1.5, 2.0),
            Arguments.of(200.0, ButtonCalculatorArithmetic.Subtraction, 100.0, 100.0),
            Arguments.of(100000.0, ButtonCalculatorArithmetic.Subtraction, 200000.0, -100000.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorArithmetic.Subtraction, Double.MIN_VALUE, Double.MAX_VALUE - Double.MIN_VALUE),
            Arguments.of(-Double.MAX_VALUE, ButtonCalculatorArithmetic.Subtraction, Double.MAX_VALUE, -Double.MAX_VALUE - Double.MAX_VALUE),
        )
    }

    fun provideMultiplicationArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorArithmetic.Multiplication, 2.0, 2.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Multiplication, 1.0, 0.0),
            Arguments.of(0.0, ButtonCalculatorArithmetic.Multiplication, -1.0, 0.0),
            Arguments.of(3.5, ButtonCalculatorArithmetic.Multiplication, 4.4, 15.4),
            Arguments.of(10.0, ButtonCalculatorArithmetic.Multiplication, 200.0, 2000.0),
            Arguments.of(100000.0, ButtonCalculatorArithmetic.Multiplication, 200000.0, 20000000000.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorArithmetic.Multiplication, 0.0, 0.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorArithmetic.Multiplication, 1.0, Double.MAX_VALUE),
        )
    }

    fun provideDivisionArgument(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorArithmetic.Division, 2.0, 0.5),
            Arguments.of(2.0, ButtonCalculatorArithmetic.Division, 0.0, Double.NaN),
            Arguments.of(-200.0, ButtonCalculatorArithmetic.Division, 200.0, -1.0),
            Arguments.of(1000.0, ButtonCalculatorArithmetic.Division, 200000.0, 0.005),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorArithmetic.Division, 2.0, Double.MAX_VALUE / 2),
            Arguments.of(2.0, ButtonCalculatorArithmetic.Division, Double.MAX_VALUE, 2.0 / Double.MAX_VALUE)
        )
    }
}