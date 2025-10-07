package com.example.calculatorApp.testData

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.ElementLayoutPositioning
import com.example.calculatorApp.model.layout.ElementLayoutText
import com.example.calculatorApp.model.layout.HasColor
import com.example.calculatorApp.testData.expected.ExpectedLayout
import com.example.calculatorApp.ui.theme.Black

object TestDataElementLayout {

    val buttonLayoutRegularExpected = ExpectedLayout.Button(
        delegateLayout = object : ElementLayout {
            override val alignment = Alignment.Center
            override val modifier = Modifier
            override val shape = CircleShape
            override val weight = 1f
        },
        delegateText = object : ElementLayoutText {
            override val alignText = TextAlign.Center
            override val textModifier: Modifier = Modifier
            override val sizeFont = 42.sp
            override val weightFont = FontWeight.Normal
        }
    )

    val buttonLayoutWideExpected = ExpectedLayout.Button(
        delegateLayout = object : ElementLayout {
            override val alignment = Alignment.CenterStart
            override val modifier = Modifier
            override val shape = CircleShape
            override val weight = 2f
        },
        delegateText = object : ElementLayoutText {
            override val alignText = TextAlign.Center
            override val textModifier: Modifier =Modifier.fillMaxWidth(0.50f)
            override val sizeFont = 42.sp
            override val weightFont = FontWeight.Normal
        }
    )

    val displayLayoutInputExpected = ExpectedLayout.Display(
        delegateLayout = object : ElementLayout {
            override val alignment = Alignment.BottomEnd
            override val modifier = Modifier.fillMaxWidth().padding(4.dp)
            override val shape = RectangleShape
            override val weight = 2f
        },
        delegateText = object : ElementLayoutText {
            override val alignText = TextAlign.End
            override val textModifier: Modifier = Modifier
            override val sizeFont = 105.sp
            override val weightFont = FontWeight.Light
        }
    )

    val rowLayoutStandardExpected = ExpectedLayout.Row(
        delegateLayout = object : ElementLayout {
            override val alignment = Alignment.Center
            override val modifier = Modifier
            override val shape = RectangleShape
            override val weight = 1f
        },
        delegatePosition = object : ElementLayoutPositioning {
            override val alignmentVertical = Alignment.CenterVertically
            override val arrangementHorizontal = Arrangement.spacedBy(8.dp)
            override val arrangementVertical = Arrangement.spacedBy(8.dp)
        },
        delegateColor = object : HasColor {
            override val color = Black
        }
    )
}