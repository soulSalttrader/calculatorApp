package com.example.calculatorApp.model.elements.row

import com.example.calculatorApp.model.layout.ElementLayout
import com.example.calculatorApp.model.layout.row.RowLayoutStandard
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.mockk
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RowDataMockTest {

    @Nested
    inner class GetElement {

        @Test
        fun `should return the correct Row instance`() {
            val row = mockk<Row>()
            val layout = mockk<ElementLayout>()
            val rowData = RowData(element = row, layout = layout)

            row shouldBe rowData.element
        }
    }

    @Nested
    inner class GetLayout {

        @Test
        fun `should return the correct layout instance`() {
            val row = mockk<Row>()
            val layout = mockk<ElementLayout>()
            val rowData = RowData(element = row, layout = layout)

            layout shouldBe rowData.layout
        }

        @Test
        fun `should return the correct RowLayoutInput instance by default`() {
            val row = mockk<Row>()
            val rowData = RowData(element = row)

            rowData.layout.shouldBeInstanceOf<RowLayoutStandard>()
        }
    }
}