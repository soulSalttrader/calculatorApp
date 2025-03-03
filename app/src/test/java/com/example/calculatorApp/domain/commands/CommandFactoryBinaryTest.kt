package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.utils.ButtonCalculatorList
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CommandFactoryBinaryTest {

    private val engine: EngineState = mockk()
    private lateinit var factory: CommandFactoryBinary

    @BeforeEach
    fun setUp() {
        clearMocks(engine)
        factory = CommandFactoryBinary(engine)
    }

    @Nested
    inner class Create {

        @Test
        fun `should create CommandHandlerBinary when Addition button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorBinary.Addition
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerBinary>()
        }

        @Test
        fun `should create CommandHandlerBinary when Subtraction button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorBinary.Subtraction
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerBinary>()
        }

        @Test
        fun `should create CommandHandlerBinary when Multiplication button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorBinary.Multiplication
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerBinary>()
        }

        @Test
        fun `should create CommandHandlerBinary when Division button is pressed`() {
            // Arrange:
            val button = ButtonCalculatorBinary.Division
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandHandlerBinary>()
        }
    }
}