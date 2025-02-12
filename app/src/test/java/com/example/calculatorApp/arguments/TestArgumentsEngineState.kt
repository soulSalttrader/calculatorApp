package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

object TestArgumentsEngineState {

    fun provideArithmeticArguments(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(ButtonCalculatorArithmetic.Division, 1.0, 2.0, 0.5),
            Arguments.of(ButtonCalculatorArithmetic.Division, 2.0, 0.0, Double.NaN),
            Arguments.of(ButtonCalculatorArithmetic.Multiplication, 1.0, 2.0, 2.0),
            Arguments.of(ButtonCalculatorArithmetic.Multiplication, 0.0, 1.0, 0.0),
            Arguments.of(ButtonCalculatorArithmetic.Subtraction, 0.0, -1.0, 1.0),
            Arguments.of(ButtonCalculatorArithmetic.Subtraction, 3.5, 1.5, 2.0),
            Arguments.of(ButtonCalculatorArithmetic.Addition, 100.0, 200.0, 300.0),
            Arguments.of(ButtonCalculatorArithmetic.Addition, 100000.0, 200000.0, 300000.0),
        )
    }
}