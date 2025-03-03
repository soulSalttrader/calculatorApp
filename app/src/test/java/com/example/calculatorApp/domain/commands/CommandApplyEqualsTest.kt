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

//class CommandApplyEqualsTest {
//
//    private val engineState: EngineState = mockk(relaxed = true)
//    private lateinit var state: CalculatorState
//    private lateinit var command: CommandApplyEquals
//
//    @BeforeEach
//    fun setUp() {
//        // Arrange:
//        clearMocks(engineState)
//        state = CalculatorState()
//        command = CommandApplyEquals(engineState)
//    }
//
//    @Nested
//    inner class Execute {
//
//        @Test
//        fun `should call applyEquals on engine`() {
//            // Act:
//            command.execute(state)
//
//            // Assert:
//            verify(exactly = 1) { engineState.applyEquals(state) }
//            confirmVerified(engineState)
//        }
//    }
//}