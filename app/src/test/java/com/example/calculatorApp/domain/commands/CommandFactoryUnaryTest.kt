package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CommandFactoryUnaryTest {

    private val engine: EngineState = mockk()
    private lateinit var factory: CommandFactoryUnary

    @BeforeEach
    fun setUp() {
        clearMocks(engine)
        factory = CommandFactoryUnary(engine)
    }

    @Nested
    inner class Create {
        @Test
        fun `should create CommandApplyPercent when Percent button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorUnary.Percentage
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandApplyPercent>()

        }

        @Test
        fun `should create CommandApplySign when PlusMinus button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorUnary.Sign
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandApplySign>()
        }
    }
}

