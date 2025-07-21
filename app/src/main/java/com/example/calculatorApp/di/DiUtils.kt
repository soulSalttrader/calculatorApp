package com.example.calculatorApp.di

import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.button.ButtonCategory

object DiUtils {

    fun ElementCategory<*>.toButtonCategoryHiltKey(): ButtonCategoryHiltKey = when (this) {
        ButtonCategory.Binary -> ButtonCategoryHiltKey.BINARY
        ButtonCategory.Unary -> ButtonCategoryHiltKey.UNARY
        ButtonCategory.Control -> ButtonCategoryHiltKey.CONTROL
        ButtonCategory.Number -> ButtonCategoryHiltKey.NUMBER
        ButtonCategory.Parenthesis -> ButtonCategoryHiltKey.PARENTHESIS

        else -> throw IllegalArgumentException("Invalid button category.")
    }
}