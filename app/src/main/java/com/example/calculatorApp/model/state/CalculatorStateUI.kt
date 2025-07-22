package com.example.calculatorApp.model.state

import android.os.Parcelable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.model.state.CalculatorStateUIDefault.defaultStyle
import kotlinx.parcelize.Parcelize

@Parcelize
data class CalculatorStateUI(
    val textSize: Float = defaultStyle.fontSize.value,
    val textWeight: Int = defaultStyle.fontWeight?.weight ?: FontWeight.Light.weight,
    val textColor: Long = defaultStyle.color.value.toLong(),
    val shouldDraw: Boolean = false,
    val buttonWidth: Float = 0f,
) : Parcelable {

    fun toTextStyle(): TextStyle = TextStyle(
        fontSize = textSize.sp,
        fontWeight = FontWeight(textWeight),
        color = Color(textColor)
    )

    companion object {
        val DEFAULT = CalculatorStateUI()
    }
}