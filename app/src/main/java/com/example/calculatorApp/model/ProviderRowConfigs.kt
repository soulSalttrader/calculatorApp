package com.example.calculatorApp.model

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.elements.row.RowCalculatorStandard

object ProviderRowConfigs {
    fun buttonSequence(vararg buttons: Button): Sequence<ButtonData> {
        return buttons.asSequence().map { ButtonData(element = it) }
    }

    val standardRow1: RowCalculatorStandard = RowCalculatorStandard.Standard1(
        buttonSequence(
            ButtonCalculatorControl.AllClear,
            ButtonCalculatorUnary.Sign,
            ButtonCalculatorUnary.Percentage,
            ButtonCalculatorBinary.Division
        )
    ).also { it.validateButtonCount(it.buttons, 4) }

    val standardRow2: RowCalculatorStandard = RowCalculatorStandard.Standard2(
        buttonSequence(
            ButtonCalculatorNumber.Seven,
            ButtonCalculatorNumber.Eight,
            ButtonCalculatorNumber.Nine,
            ButtonCalculatorBinary.Multiplication
        )
    ).also { it.validateButtonCount(it.buttons, 4) }

    val standardRow3: RowCalculatorStandard = RowCalculatorStandard.Standard3(
        buttonSequence(
            ButtonCalculatorNumber.Four,
            ButtonCalculatorNumber.Five,
            ButtonCalculatorNumber.Six,
            ButtonCalculatorBinary.Subtraction
        )
    ).also { it.validateButtonCount(it.buttons, 4) }

    val standardRow4: RowCalculatorStandard = RowCalculatorStandard.Standard4(
        buttonSequence(
            ButtonCalculatorNumber.One,
            ButtonCalculatorNumber.Two,
            ButtonCalculatorNumber.Three,
            ButtonCalculatorBinary.Addition
        )
    ).also { it.validateButtonCount(it.buttons, 4) }

    val standardRow5: RowCalculatorStandard = RowCalculatorStandard.Standard5(
        buttonSequence(
            ButtonCalculatorNumber.Zero,
            ButtonCalculatorControl.Decimal,
            ButtonCalculatorControl.Equals
        )
    ).also { it.validateButtonCount(it.buttons, 3) }

    val standardRows: Sequence<RowCalculatorStandard> = sequenceOf(
        standardRow1,
        standardRow2,
        standardRow3,
        standardRow4,
        standardRow5
    )
}

