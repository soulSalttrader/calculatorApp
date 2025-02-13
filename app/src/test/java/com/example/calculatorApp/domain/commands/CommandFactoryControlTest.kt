package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CommandFactoryControlTest {

    private val engine: EngineState = mockk()
    private lateinit var factory: CommandFactoryControl

    @BeforeEach
    fun setUp() {
        clearMocks(engine)
        factory = CommandFactoryControl(engine)
    }

    @Nested
    inner class Create {

        @Test
        fun `should create CommandEnterDecimal when Decimal button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.Decimal
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandEnterDecimal>()
        }

        @Test
        fun `should create CommandApplyClearAll when AllClear button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.AllClear
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandApplyClearAll>()

        }
        @Test
        fun `should create CommandApplyClear when Clear button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.Clear
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandApplyClear>()

        }
        @Test
        fun `should create CommandApplyPercent when Percent button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.Percent
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandApplyPercent>()

        }
        @Test
        fun `should create CommandApplySign when PlusMinus button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.PlusMinus
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandApplySign>()
        }
    }
}