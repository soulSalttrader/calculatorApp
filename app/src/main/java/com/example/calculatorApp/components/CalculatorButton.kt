package com.example.calculatorApp.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonData
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular
import com.example.calculatorApp.model.styles.StyleCalculator

@Composable
fun CalculatorButton(paramsButton: ParamsButton) {

    val styledBoxParams = ParamsStyledBox(
        visuals = paramsButton.visuals,
        semantics = paramsButton.semantics
    ) {
        Text(
            text = paramsButton.data.element.symbol.label,
            fontSize = paramsButton.data.layout.sizeFont,
            fontWeight = paramsButton.data.layout.weightFont,
            textAlign = paramsButton.data.layout.alignText,
            color = paramsButton.visuals.foregroundColor,
            modifier = paramsButton.data.layout.textModifier,
        )
    }

    CalculatorStyledBox(styledBoxParams)
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun PreviewCalculatorButtonDefault() {
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

    val paramsButton = ParamsButton(
        data = buttonData,
        visuals = visuals,
        semantics = semantics
    )

    CalculatorButton(paramsButton)
}