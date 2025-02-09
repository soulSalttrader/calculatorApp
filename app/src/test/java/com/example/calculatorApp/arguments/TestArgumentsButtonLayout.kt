package com.example.calculatorApp.arguments

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular
import com.example.calculatorApp.model.layout.button.ButtonLayoutWide
import org.junit.jupiter.params.provider.Arguments
import java.util.stream.Stream

data class TestArgumentsButtonLayout(
    val regular: ButtonLayoutRegular = ButtonLayoutRegular(),
    val wide: ButtonLayoutWide = ButtonLayoutWide(),
) : TestArguments {

    fun provideRegularProperties(): Stream<Arguments> {
       return Stream.of(
            Arguments.of(regular.alignment, Alignment.Center, "alignment"),
            Arguments.of(regular.modifier, Modifier, "modifier"),
            Arguments.of(regular.shape, CircleShape, "shape"),
            Arguments.of(regular.weight, 1f, "weight"),

           Arguments.of(regular.alignText, TextAlign.Center, "textAlign"),
            Arguments.of(regular.sizeFont, 42.sp, "fontSize"),
            Arguments.of(regular.weightFont, FontWeight.Normal, "fontWeight"),
        )
    }

    fun provideWideProperties(): Stream<Arguments> {
        return Stream.of(
            Arguments.of(wide.alignment, Alignment.Center, "alignment"),
            Arguments.of(wide.modifier, Modifier, "modifier"),
            Arguments.of(wide.shape, CircleShape, "shape"),
            Arguments.of(wide.weight, 2f, "weight"),

            Arguments.of(wide.alignText, TextAlign.Center, "textAlign"),
            Arguments.of(wide.sizeFont, 42.sp, "fontSize"),
            Arguments.of(wide.weightFont, FontWeight.Normal, "fontWeight"),
        )
    }
}