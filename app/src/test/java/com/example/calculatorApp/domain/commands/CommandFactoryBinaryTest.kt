package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
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

        // Arrange:
        fun provideArguments(): Array<ButtonCalculatorBinary> {
            return ButtonCalculatorList.binary
        }

        @Disabled("Deprecated")
        @Test
        fun `should create CommandApplyArithmetic when Equals button is pressed`() {
            // Arrange:

            // Act:

            // Assert:
        }

        @Disabled("deprecated")
        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should create CommandChain with applyArithmetic and EnterArithmetic for other Arithmetic buttons`(
            button: ButtonCalculatorBinary
        ) {
            // Act:
            val command = factory.create(button)

            // Assert:
            command.shouldBeInstanceOf<CommandChain>()
        }
    }
}