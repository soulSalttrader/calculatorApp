package com.example.calculatorApp.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConversionException
import org.junit.jupiter.params.converter.ArgumentConverter

object ColorToLongConverter : ArgumentConverter {

    override fun convert(source: Any?, context: ParameterContext?): Any {
        return (source as? Color)
            ?.toArgb()
            ?.toLong()
            ?: throw ArgumentConversionException("The argument should be a Color object: $source.")
    }
}