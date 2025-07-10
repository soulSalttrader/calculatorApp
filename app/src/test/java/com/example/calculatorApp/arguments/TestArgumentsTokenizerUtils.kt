package com.example.calculatorApp.arguments

import com.example.calculatorApp.testData.TestDataTokenizerUtils
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.utils.ButtonCalculatorList.provideSequenceButtons
import kotlin.reflect.KClass

object TestArgumentsTokenizerUtils {

    fun provide(
        buttonClass: KClass<out Button>,
        buttons: Sequence<Button> = provideSequenceButtons(buttonClass)
    ): Sequence<TestDataTokenizerUtils> {
        return buttons.map { TestDataTokenizerUtils(it) }
    }
}