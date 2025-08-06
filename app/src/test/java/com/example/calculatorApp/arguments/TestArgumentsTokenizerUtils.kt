package com.example.calculatorApp.arguments

import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.testData.TestDataTokenizerUtils
import com.example.calculatorApp.utils.TestUtils.mapSingletonsTo
import kotlin.reflect.KClass

object TestArgumentsTokenizerUtils {

    fun <T : Button> provideUtils(
        type: KClass<out T>,
    ): Sequence<TestDataTokenizerUtils> = mapSingletonsTo(type) {
        TestDataTokenizerUtils(it)
    }
}