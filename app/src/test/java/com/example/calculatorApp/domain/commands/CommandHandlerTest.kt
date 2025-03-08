package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.model.state.CalculatorState
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CommandHandlerTest {

    private val engineState: EngineState = mockk(relaxed = true)
    private lateinit var state: CalculatorState

    @Nested
    inner class HandleBinary {

        private lateinit var button: ButtonCalculatorBinary
        private lateinit var command: CommandHandler<ButtonCalculatorBinary>

        @BeforeEach
        fun setUp() {
            // Arrange:
            clearMocks(engineState)
            state = CalculatorState()
            button = ButtonCalculatorBinary.Addition
            command = CommandHandler(engineState, button)
        }

        @Test
        fun `should invoke handleBinary on EngineState`() {
            // Act:
            command.execute(state)

            // Assert:
            verify(exactly = 1) { engineState.handleBinary(state, button) }
            confirmVerified(engineState)
        }
    }

    @Nested
    inner class HandleUnary {

        private lateinit var button: ButtonCalculatorUnary
        private lateinit var command: CommandHandler<ButtonCalculatorUnary>

        @BeforeEach
        fun setUp() {
            // Arrange:
            clearMocks(engineState)
            state = CalculatorState()
            button = ButtonCalculatorUnary.Sign
            command = CommandHandler(engineState, button)
        }

        @Test
        fun `should invoke handleUnary on EngineState`() {
            // Act:
            command.execute(state)

            // Assert:
            verify(exactly = 1) { engineState.handleUnary(state, button) }
            confirmVerified(engineState)
        }
    }

    @Nested
    inner class HandleControl {

        private lateinit var button: ButtonCalculatorControl
        private lateinit var command: CommandHandler<ButtonCalculatorControl>

        @BeforeEach
        fun setUp() {
            // Arrange:
            clearMocks(engineState)
            state = CalculatorState()
            button = ButtonCalculatorControl.Clear
            command = CommandHandler(engineState, button)
        }

        @Test
        fun `should invoke handleControl on EngineState`() {
            // Act:
            command.execute(state)

            // Assert:
            verify(exactly = 1) { engineState.handleControl(state, button) }
            confirmVerified(engineState)
        }
    }

    @Nested
    inner class HandleNumber {

        private lateinit var button: ButtonCalculatorNumber
        private lateinit var command: CommandHandler<ButtonCalculatorNumber>

        @BeforeEach
        fun setUp() {
            // Arrange:
            clearMocks(engineState)
            state = CalculatorState()
            button = ButtonCalculatorNumber.Three
            command = CommandHandler(engineState, button)
        }

        @Test
        fun `should invoke handleNumber on EngineState`() {
            // Act:
            command.execute(state)

            // Assert:
            verify(exactly = 1) { engineState.handleNumber(state, button) }
            confirmVerified(engineState)
        }
    }
}