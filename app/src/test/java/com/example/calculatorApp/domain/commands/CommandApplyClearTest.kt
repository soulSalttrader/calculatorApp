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

//class CommandApplyClearTest {
//
//    private val engineState: EngineState = mockk(relaxed = true)
//    private lateinit var state: CalculatorState
//    private lateinit var command: CommandApplyClear
//
//    @BeforeEach
//    fun setUp() {
//        // Arrange:
//        clearMocks(engineState)
//        state = CalculatorState()
//        command = CommandApplyClear(engineState)
//    }
//
//    @Nested
//    inner class Execute {
//
//        @Test
//        fun `should call applyClear on engine`() {
//            // Act:
//            command.execute(state)
//
//            // Assert:
//            verify(exactly = 1) { engineState.applyClear(state) }
//            confirmVerified(engineState)
//        }
//    }
//}














