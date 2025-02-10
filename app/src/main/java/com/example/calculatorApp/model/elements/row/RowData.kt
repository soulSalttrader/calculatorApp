package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.model.elements.ElementCategory
import com.example.calculatorApp.model.elements.ElementCategoryStyleCollection
import com.example.calculatorApp.model.elements.ElementColorStyle
import com.example.calculatorApp.model.elements.ElementData
import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.row.RowLayoutStandard

class RowData(
    override val element: Row,
    override val layout: ElementLayout = RowLayoutStandard(),
) : ElementData<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementLayout, ElementColorStyle>