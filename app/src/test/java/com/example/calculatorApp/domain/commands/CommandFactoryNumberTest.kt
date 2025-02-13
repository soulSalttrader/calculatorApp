package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.utils.ButtonCalculatorList
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.clearMocks
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CommandFactoryNumberTest {

    private val engine: EngineState = mockk()
    private lateinit var factory: CommandFactoryNumber

    @BeforeEach
    fun setUp() {
        clearMocks(engine)
        factory = CommandFactoryNumber(engine)
    }

    @Nested
    inner class Create {

        // Arrange:
        fun provideArguments(): Array<ButtonCalculatorNumber> {
           return ButtonCalculatorList.numbers
        }

        @ParameterizedTest
        @MethodSource("provideArguments")
        fun `should create CommandEnterNumber with correct number when ButtonCalculatorNumber button is pressed`(
            button: ButtonCalculatorNumber
        ) {
            // Act:
            val command = factory.create(button)
            // Assert:
            command.shouldBeInstanceOf<CommandEnterNumber>()
        }
    }
}