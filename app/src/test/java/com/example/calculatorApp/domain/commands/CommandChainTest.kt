package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.model.state.CalculatorState
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyOrder
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CommandChainTest {

    private val command1: Command = mockk()
    private val command2: Command = mockk()
    private val command3: Command = mockk()
    private lateinit var state: CalculatorState
    private lateinit var commandChain: CommandChain

    @BeforeEach
    fun setUp() {
        clearMocks(command1, command2, command3)
        state = CalculatorState(lastInput = "10")
        commandChain = CommandChain(command1, command2, command3)
    }

    @Nested
    inner class Execute {

        @Test
        fun `should execute all commands in the chain in the correct order`() {
            // Arrange:
            val stateAfterCommand1 = state.copy(lastInput = "20")
            val stateAfterCommand2 = stateAfterCommand1.copy(lastInput = "30")
            val finalState = stateAfterCommand2.copy(lastInput = "40")

            every { command1.execute(state) } returns stateAfterCommand1
            every { command2.execute(stateAfterCommand1) } returns stateAfterCommand2
            every { command3.execute(stateAfterCommand2) } returns finalState

            // Act:
            val result = commandChain.execute(state)

            // Assert:
            verifyOrder {
                command1.execute(state)
                command2.execute(stateAfterCommand1)
                command3.execute(stateAfterCommand2)
            }

            result shouldBe finalState
        }
    }
}