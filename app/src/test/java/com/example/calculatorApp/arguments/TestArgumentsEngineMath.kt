package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
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

            Arguments.of(-1, 1),
            Arguments.of(-5, 5),
            Arguments.of(-99, 99),
            Arguments.of(-0, 0),
            Arguments.of(-999, 999),
        )
    }

    fun providesPercentArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(22.0, ButtonCalculatorBinary.Addition, 3.0, 0.66),
            Arguments.of(329.0, ButtonCalculatorBinary.Multiplication, 100.0, 1.0),
            Arguments.of(1000.0, ButtonCalculatorBinary.Division, 100.0, 1.0),

            Arguments.of( -1.0, ButtonCalculatorBinary.Subtraction, 50.0, -0.5),
            Arguments.of( -329.0, ButtonCalculatorBinary.Addition, 1.0, -3.29),
            Arguments.of( -1000.0, ButtonCalculatorBinary.Multiplication, 0.0, 0.0),

            Arguments.of( 0.01, ButtonCalculatorBinary.Division, 100.0, 1.0),
            Arguments.of( 0.329, ButtonCalculatorBinary.Subtraction, 10.0, 0.0329),
            Arguments.of( 0.999, ButtonCalculatorBinary.Addition, 5.0, 0.04995),

            Arguments.of( -0.01, ButtonCalculatorBinary.Multiplication, 100.0, 1.0),
            Arguments.of( -0.329, ButtonCalculatorBinary.Division, 20.0, 0.2),
            Arguments.of( -0.999, ButtonCalculatorBinary.Subtraction, 80.0, -0.7992),

            Arguments.of( -0.01, ButtonCalculatorBinary.Addition, 11.1, -0.00111),
            Arguments.of( -0.329, ButtonCalculatorBinary.Multiplication, 20.55, 0.2055),
            Arguments.of( -0.999, ButtonCalculatorBinary.Division, 80.99, 0.8099),

            Arguments.of( 0.0, ButtonCalculatorBinary.Subtraction, 0.0, 0.0),
            Arguments.of( null, ButtonCalculatorBinary.Addition, 22.0, 0.22),
            Arguments.of( null, null, 22.0, 0.22),
        )
    }

    fun provideAdditionArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorBinary.Addition, 2.0, 3.0),
            Arguments.of(0.0, ButtonCalculatorBinary.Addition, 1.0, 1.0),
            Arguments.of(0.0, ButtonCalculatorBinary.Addition, -1.0, -1.0),
            Arguments.of(3.5, ButtonCalculatorBinary.Addition, 4.4, 7.9),
            Arguments.of(100.0, ButtonCalculatorBinary.Addition, 200.0, 300.0),
            Arguments.of(100000.0, ButtonCalculatorBinary.Addition, 200000.0, 300000.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorBinary.Addition, Double.MAX_VALUE, Double.MAX_VALUE + Double.MAX_VALUE),
            Arguments.of(Double.MIN_VALUE, ButtonCalculatorBinary.Addition, Double.MIN_VALUE, Double.MIN_VALUE + Double.MIN_VALUE),
        )
    }

    fun provideSubtractionArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorBinary.Subtraction, 2.0, -1.0),
            Arguments.of(0.0, ButtonCalculatorBinary.Subtraction, 1.0, -1.0),
            Arguments.of(0.0, ButtonCalculatorBinary.Subtraction, -1.0, 1.0),
            Arguments.of(3.5, ButtonCalculatorBinary.Subtraction, 1.5, 2.0),
            Arguments.of(200.0, ButtonCalculatorBinary.Subtraction, 100.0, 100.0),
            Arguments.of(100000.0, ButtonCalculatorBinary.Subtraction, 200000.0, -100000.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorBinary.Subtraction, Double.MIN_VALUE, Double.MAX_VALUE - Double.MIN_VALUE),
            Arguments.of(-Double.MAX_VALUE, ButtonCalculatorBinary.Subtraction, Double.MAX_VALUE, -Double.MAX_VALUE - Double.MAX_VALUE),
        )
    }

    fun provideMultiplicationArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorBinary.Multiplication, 2.0, 2.0),
            Arguments.of(0.0, ButtonCalculatorBinary.Multiplication, 1.0, 0.0),
            Arguments.of(0.0, ButtonCalculatorBinary.Multiplication, -1.0, 0.0),
            Arguments.of(3.5, ButtonCalculatorBinary.Multiplication, 4.4, 15.4),
            Arguments.of(10.0, ButtonCalculatorBinary.Multiplication, 200.0, 2000.0),
            Arguments.of(100000.0, ButtonCalculatorBinary.Multiplication, 200000.0, 20000000000.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorBinary.Multiplication, 0.0, 0.0),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorBinary.Multiplication, 1.0, Double.MAX_VALUE),
        )
    }

    fun provideDivisionArgument(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(1.0, ButtonCalculatorBinary.Division, 2.0, 0.5),
            Arguments.of(2.0, ButtonCalculatorBinary.Division, 0.0, Double.NaN),
            Arguments.of(-200.0, ButtonCalculatorBinary.Division, 200.0, -1.0),
            Arguments.of(1000.0, ButtonCalculatorBinary.Division, 200000.0, 0.005),
            Arguments.of(Double.MAX_VALUE, ButtonCalculatorBinary.Division, 2.0, Double.MAX_VALUE / 2),
            Arguments.of(2.0, ButtonCalculatorBinary.Division, Double.MAX_VALUE, 2.0 / Double.MAX_VALUE)
        )
    }
}