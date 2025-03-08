package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.state.CalculatorState
import io.mockk.clearMocks
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

//class CommandHandlerControlTest {
//
//    private val engineState: EngineState = mockk(relaxed = true)
//    private lateinit var state: CalculatorState
//    private lateinit var control: ButtonCalculatorControl
//    private lateinit var command: CommandHandlerControl
//
//    @BeforeEach
//    fun setUp() {
//        // Arrange:
//        clearMocks(engineState)
//        state = CalculatorState()
//        control= ButtonCalculatorControl.Clear
//        command = CommandHandlerControl(engineState, control)
//    }
//
//    @Nested
//    inner class Execute {
//
//        @Test
//        fun `should call execute on engine`() {
//            // Act:
//            command.execute(state)
//
//            // Assert:
//            verify(exactly = 1) { engineState.handleControl(state, control) }
//            confirmVerified(engineState)
//        }
//    }
//}