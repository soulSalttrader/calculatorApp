package com.example.calculatorApp.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.button.ButtonLayoutRegular

@Composable
fun CalculatorStyledBox(
    modifier: Modifier = Modifier,
    role: Role? = null,
    layout: ElementLayout? = null,
    backgroundColor: Color,
    alignment: Alignment = Alignment.Center,
    shape: Shape = CircleShape,
    contentDescription: String? = null,
    content: @Composable BoxScope.() -> Unit,
) {

    val finalAlignment = layout?.alignment ?: alignment
    val finalShape = layout?.shape ?: shape
    val finalModifier = (layout?.modifier ?: Modifier).then(modifier)

    Box(
        contentAlignment = finalAlignment,
        modifier = finalModifier
            .clip(finalShape)
            .background(backgroundColor)
            .semantics {
                contentDescription?.takeIf { it.isNotBlank() }?.let { this.contentDescription = it }
                role?.let { this.role = it }
            },
        content = content
    )
}

@Preview
@Composable
fun PreviewDigitButton() {
    val digitLayout = ButtonLayoutRegular()
    CalculatorStyledBox(
        layout = digitLayout,
        backgroundColor = Color.DarkGray,
        role = Role.Button,
        contentDescription = "Button 3"
    ) {
        Text(
            text = "0",
            textAlign = digitLayout.alignText,
            fontSize = digitLayout.sizeFont,
            fontWeight = digitLayout.weightFont,
            color = Color.Blue
        )
    }
}