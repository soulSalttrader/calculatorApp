package com.example.calculatorApp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular
import com.example.calculatorApp.model.styles.StyleCalculator

@Composable
fun CalculatorStyledBox(paramsStyledBox: ParamsStyledBox) {
    Box(
        contentAlignment = paramsStyledBox.visuals.layout.alignment,
        modifier = paramsStyledBox.visuals.modifier
            .clip(paramsStyledBox.visuals.layout.shape)
            .background(paramsStyledBox.visuals.backgroundColor)
            .semantics {
                paramsStyledBox.semantics.contentDescription?.takeIf { it.isNotBlank() }?.let { this.contentDescription = it }
                paramsStyledBox.semantics.role?.let { this.role = it }
            },
        content = paramsStyledBox.content
    )
}

@Preview
@Composable
fun PreviewDigitButton() {
    val digitLayout = ButtonLayoutRegular()

    val buttonData = ButtonData(
        element = ButtonCalculatorBinary.Multiplication,
        layout = ButtonLayoutRegular()
    )

    val style = StyleCalculator.Standard

    val visuals = BoxVisuals(
        modifier = Modifier,
        layout = buttonData.layout,
        backgroundColor = buttonData.element.getBackgroundColor(style.buttonStyle),
        foregroundColor = buttonData.element.getForegroundColor(style.buttonStyle),
    )

    val semantics = BoxSemantics(
        role = Role.Button,
        contentDescription = buttonData.element.symbol.label
    )

    val paramsStyledBox = ParamsStyledBox(
        visuals = visuals,
        semantics = semantics,
    ) {
        Text(
            text = "0",
            textAlign = digitLayout.alignText,
            fontSize = digitLayout.sizeFont,
            fontWeight = digitLayout.weightFont,
            color = Color.Blue
        )
    }

    CalculatorStyledBox(paramsStyledBox = paramsStyledBox)
}