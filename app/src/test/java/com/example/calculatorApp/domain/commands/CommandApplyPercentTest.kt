package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.state.CalculatorState
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CommandApplyPercentTest {

    private val engineState: EngineState = mockk(relaxed = true)
    private lateinit var state: CalculatorState
    private lateinit var command: CommandApplyPercent

    @BeforeEach
    fun setUp() {
        // Arrange:
        clearMocks(engineState)
        state = CalculatorState()
        command = CommandApplyPercent(engineState)
    }

    @Nested
    inner class Execute {

        @Test
        fun `should call applyPercent on engine`() {
            // Act:
            command.execute(state)

            // Assert:
            verify(exactly = 1) { engineState.applyPercent(state) }
            confirmVerified(engineState)
        }
    }
}