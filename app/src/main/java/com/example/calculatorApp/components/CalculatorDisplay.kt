package com.example.calculatorApp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.model.elements.display.DisplayCalculatorInput
import com.example.calculatorApp.model.elements.display.DisplayData
import com.example.calculatorApp.model.styles.StyleCalculator
import com.example.calculatorApp.utils.Constants.SIZE_FONT_DEFAULT

@Composable
fun CalculatorDisplay(paramsStyledText: ParamsStyledText) {

    CalculatorStyledBox(
        modifier = paramsStyledText.paramsDisplayText.modifier,
        layout = paramsStyledText.paramsDisplayText.data.layout,
        backgroundColor = paramsStyledText.paramsDisplayText.data.element.getBackgroundColor(paramsStyledText.paramsDisplayText.style.displayStyle)
    ) {

        Text(
            text = paramsStyledText.paramsDisplayText.data.getPlaceholderText(),
            textAlign = paramsStyledText.paramsDisplayText.data.layout.alignText,
            fontWeight = paramsStyledText.paramsDisplayText.data.layout.weightFont,
            fontSize = paramsStyledText.paramsDisplayText.data.layout.sizeFont,
            color = Color.Transparent
        )

        CalculatorStyledText(paramsStyledText = paramsStyledText)
    }
}

@Preview(showBackground = false, widthDp = 320, heightDp = 100, name = "CalculatorDisplay")
@Composable
fun PreviewCalculatorDisplay() {

    val paramsDisplayText = ParamsDisplayText(
        modifier = Modifier.fillMaxWidth(),
        text = "123.45",
        data = DisplayData(DisplayCalculatorInput.Standard),
        style = StyleCalculator.Standard,
        textStyle = TextStyle(
            fontSize = SIZE_FONT_DEFAULT.sp,
            fontWeight = FontWeight.Light
        ),
        shouldDraw = true,
        resizedTextStyle = TextStyle.Default,
    )

    val paramsTextStyle = ParamsTextStyle(
        onSetInitialTextStyle = { /* No-op */ },
        onAdjustTextStyle = { _, _, _, _, _ -> /* No-op */ }
    )

    val paramsStyledText = ParamsStyledText(
        paramsDisplayText = paramsDisplayText,
        paramsTextStyle = paramsTextStyle,
    )

    CalculatorDisplay(paramsStyledText = paramsStyledText)
}