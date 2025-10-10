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
    val displayData = paramsStyledText.paramsDisplayText.data
    val displayStyle = paramsStyledText.paramsDisplayText.style.displayStyle

    val visuals = BoxVisuals(
        modifier = paramsStyledText.paramsDisplayText.modifier,
        layout = displayData.layout,
        backgroundColor = displayData.element.getBackgroundColor(displayStyle),
        foregroundColor = displayData.element.getForegroundColor(displayStyle),
    )

    val semantics = BoxSemantics(
        contentDescription = paramsStyledText.paramsDisplayText.data.element::class.simpleName
    )

    val paramsStyledBox = ParamsStyledBox(
        visuals = visuals,
        semantics = semantics
    ) {
        Text(
            text = displayData.getPlaceholderText(),
            textAlign = displayData.layout.alignText,
            fontWeight = displayData.layout.weightFont,
            fontSize = displayData.layout.sizeFont,
            color = Color.Transparent
        )

        CalculatorStyledText(paramsStyledText = paramsStyledText)
    }

    CalculatorStyledBox(paramsStyledBox = paramsStyledBox)
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
        resizedTextStyle = TextStyle(
            fontSize = SIZE_FONT_DEFAULT.sp,
            fontWeight = FontWeight.Light
        ),
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