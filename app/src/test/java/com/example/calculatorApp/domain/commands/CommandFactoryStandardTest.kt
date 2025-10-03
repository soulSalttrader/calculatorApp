package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.EngineState
import com.example.calculatorApp.domain.action.CalculatorAction
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
import com.example.calculatorApp.testData.TestDataElement
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.streams.asStream

@ExtendWith(MockKExtension::class)
class CommandFactoryStandardTest {

    private val engineState: EngineState = mockk()
    private lateinit var factory: CommandFactoryStandard

    @BeforeEach
    fun setUp() {
        clearMocks(engineState)
        factory = CommandFactoryStandard(engineState)
    }

    @Nested
    inner class Create {

        // Arrange:
        private fun provideArithmetics(): Stream<Button> {
            return TestDataElement.buttonsBinaryTest.asStream()
        }

        // Arrange:
        private fun provideUnary(): Stream<Button> {
            return TestDataElement.buttonsUnaryTest.asStream()
        }

        // Arrange:
        private fun provideControls(): Stream<Button> {
            return TestDataElement.buttonsControlsTest.asStream()
        }

        // Arrange:
        private fun provideNumbers(): Stream<Button> {
            return TestDataElement.buttonsNumbersTest.asStream()
        }

        @ParameterizedTest
        @MethodSource("provideArithmetics")
        fun `should create CommandHandler for binary buttons`(button: ButtonCalculatorBinary) {
            val action = CalculatorAction.ButtonPressed(button)

            // Act
            val command = factory.createCommand(action)

            // Assert
            command shouldBe CommandHandler(engineState, button)
        }

        @ParameterizedTest
        @MethodSource("provideUnary")
        fun `should create CommandHandler for unary buttons`(button: ButtonCalculatorUnary) {
            val action = CalculatorAction.ButtonPressed(button)

            // Act
            val command = factory.createCommand(action)

            // Assert
            command shouldBe CommandHandler(engineState, button)
        }

        @ParameterizedTest
        @MethodSource("provideControls")
        fun `should create CommandHandler for control buttons`(button: ButtonCalculatorControl) {
            val action = CalculatorAction.ButtonPressed(button)

            // Act
            val command = factory.createCommand(action)

            // Assert
            command shouldBe CommandHandler(engineState, button)
        }

        @ParameterizedTest
        @MethodSource("provideNumbers")
        fun `should create CommandHandler for number buttons`(button: ButtonCalculatorNumber) {
            val action = CalculatorAction.ButtonPressed(button)

            // Act
            val command = factory.createCommand(action)

            // Assert
            command shouldBe CommandHandler(engineState, button)
        }
    }
}