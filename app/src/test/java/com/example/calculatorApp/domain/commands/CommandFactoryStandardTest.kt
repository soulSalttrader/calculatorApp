package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.actions.CalculatorAction
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorArithmetic
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.utils.ButtonCalculatorList
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class CommandFactoryStandardTest {

    private val arithmeticFactory: CommandFactoryArithmetic = mockk()
    private val controlFactory: CommandFactoryControl = mockk()
    private val numberFactory: CommandFactoryNumber = mockk()
    private lateinit var factory: CommandFactoryStandard

    @BeforeEach
    fun setUp() {
        clearMocks(arithmeticFactory, controlFactory, numberFactory)
        factory = CommandFactoryStandard(arithmeticFactory, controlFactory, numberFactory)
    }

    @Nested
    inner class Create {

        // Arrange:
        private fun provideArithmetics(): Array<ButtonCalculatorArithmetic> {
            return ButtonCalculatorList.arithmetics
        }

        // Arrange:
        private fun provideControls(): Array<ButtonCalculatorControl> {
            return ButtonCalculatorList.controls
        }

        // Arrange:
        private fun provideNumbers(): Array<ButtonCalculatorNumber> {
            return ButtonCalculatorList.numbers
        }

        @ParameterizedTest
        @MethodSource("provideArithmetics")
        fun `should create CommandApplyArithmetic or CommandChain through arithmeticCommandFactory when ButtonCalculatorArithmetic button is pressed`(
            button: ButtonCalculatorArithmetic
        ) {
            // Prepare the expected CommandEnterArithmetic
            val expectedCommand = mockk<CommandEnterArithmetic>()

            // Mock the `create` method for CommandFactoryArithmetic to return the expected command
            every { arithmeticFactory.create(button) } returns expectedCommand

            // Create the action to simulate the button press
            val action = CalculatorAction.ButtonPressed(button)

            // Act: Call the method
            val command = factory.createCommand(action)

            // Assert: Ensure the returned command is the one expected
            command shouldBe expectedCommand

            // Verify that `create` was called with the correct argument
            verify { arithmeticFactory.create(button) }
        }

        @ParameterizedTest
        @MethodSource("provideControls")
        fun `should create corresponding control command through controlCommandFactory when ButtonCalculatorControl button is pressed`(
            button: ButtonCalculatorControl
        ) {
            // Prepare the expected CommandEnterControl
            val expectedCommand = mockk<CommandEnterDecimal>()

            // Mock the `create` method for CommandFactoryArithmetic to return the expected command
            every { controlFactory.create(button) } returns expectedCommand

            // Create the action to simulate the button press
            val action = CalculatorAction.ButtonPressed(button)

            // Act: Call the method
            val command = factory.createCommand(action)

            // Assert: Ensure the returned command is the one expected
            command shouldBe expectedCommand

            // Verify that `create` was called with the correct argument
            verify { controlFactory.create(button) }
        }

        @ParameterizedTest
        @MethodSource("provideNumbers")
        fun `should create CommandEnterNumber through numberCommandFactory when ButtonCalculatorNumber button is pressed`(
            button: ButtonCalculatorNumber
        ) {
            // Prepare the expected CommandEnterNumber
            val expectedCommand = mockk<CommandEnterNumber>()

            // Mock the `create` method for CommandFactoryNumber to return the expected command
            every { numberFactory.create(button) } returns expectedCommand

            // Create the action to simulate the button press
            val action = CalculatorAction.ButtonPressed(button)

            // Act: Call the method
            val command = factory.createCommand(action)

            // Assert: Ensure the returned command is the one expected
            command shouldBe expectedCommand

            // Verify that `create` was called with the correct argument
            verify { numberFactory.create(button) }
        }

        @Test
        fun `should throw IllegalArgumentException when invalid button is pressed`() {
            // Act & Assert: Ensure that the IllegalArgumentException is thrown for invalid buttons
            val button = mockk<Button>()

            shouldThrow<IllegalArgumentException> {
                factory.createCommand(CalculatorAction.ButtonPressed(button))
            }
        }
    }
}