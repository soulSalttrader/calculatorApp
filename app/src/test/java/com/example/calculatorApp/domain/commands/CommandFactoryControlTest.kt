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
        fun `should create CommandHandlerControl when Decimal button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.Decimal
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerControl>()
        }

        @Test
        fun `should create CommandHandlerControl when AllClear button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.AllClear
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerControl>()

        }
        @Test
        fun `should create CommandHandlerControl when Clear button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorControl.Clear
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerControl>()
        }
    }
}