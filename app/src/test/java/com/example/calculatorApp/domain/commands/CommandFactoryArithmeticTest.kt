package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.utils.ButtonCalculatorList
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CommandFactoryArithmeticTest {

    private val engine: EngineState = mockk()
    private lateinit var factory: CommandFactoryArithmetic

    @BeforeEach
    fun setUp() {
        clearMocks(engine)
        factory = CommandFactoryArithmetic(engine)
    }

    @Nested
    inner class Create {

        // Arrange:
        fun provideArguments(): Array<ButtonCalculatorArithmetic> {
            return ButtonCalculatorList.arithmetics
                .filterNot { button -> button == ButtonCalculatorArithmetic.Equals }
                .toTypedArray()
        }

        @Test
        fun `should create CommandApplyArithmetic when Equals button is pressed`() {
            // Arrange:
            val equalButton = ButtonCalculatorArithmetic.Equals

            // Act:
            val command = factory.create(equalButton)

            // Assert:
            command.shouldBeInstanceOf<CommandApplyEquals>()
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should create CommandChain with applyArithmetic and EnterArithmetic for other Arithmetic buttons`(
            button: ButtonCalculatorArithmetic
        ) {
            // Act:
            val command = factory.create(button)

            // Assert:
            command.shouldBeInstanceOf<CommandChain>()
        }
    }
}