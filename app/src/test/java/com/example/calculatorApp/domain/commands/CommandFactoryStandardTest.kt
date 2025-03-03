package com.example.calculatorApp.domain.commands

import com.example.calculatorApp.domain.actions.CalculatorAction
import com.example.calculatorApp.model.elements.button.Button
import com.example.calculatorApp.model.elements.button.ButtonCalculatorBinary
import com.example.calculatorApp.model.elements.button.ButtonCalculatorControl
import com.example.calculatorApp.model.elements.button.ButtonCalculatorNumber
import com.example.calculatorApp.model.elements.button.ButtonCalculatorUnary
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

    private val binaryFactory: CommandFactoryBinary = mockk()
    private val unaryFactory: CommandFactoryUnary = mockk()
    private val controlFactory: CommandFactoryControl = mockk()
    private val numberFactory: CommandFactoryNumber = mockk()
    private lateinit var factory: CommandFactoryStandard

    @BeforeEach
    fun setUp() {
        clearMocks(binaryFactory, controlFactory, numberFactory)
        factory = CommandFactoryStandard(binaryFactory, unaryFactory, controlFactory, numberFactory)
    }

    @Nested
    inner class Create {

        // Arrange:
        private fun provideArithmetics(): Array<ButtonCalculatorBinary> {
            return ButtonCalculatorList.binary
        }

        // Arrange:
        private fun provideUnary(): Array<ButtonCalculatorUnary> {
            return ButtonCalculatorList.unary
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
        fun `should create CommandApplyBinary or CommandChain through binaryCommandFactory when ButtonCalculatorBinary button is pressed`(
            button: ButtonCalculatorBinary
        ) {
            // Prepare the expected CommandEnterBinary
            val expectedCommand = mockk<CommandEnterBinary>()

            // Mock the `create` method for CommandFactoryArithmetic to return the expected command
            every { binaryFactory.create(button) } returns expectedCommand

            // Create the action to simulate the button press
            val action = CalculatorAction.ButtonPressed(button)

            // Act: Call the method
            val command = factory.createCommand(action)

            // Assert: Ensure the returned command is the one expected
            command shouldBe expectedCommand

            // Verify that `create` was called with the correct argument
            verify { binaryFactory.create(button) }
        }

        @ParameterizedTest
        @MethodSource("provideUnary")
        fun `should create corresponding unary command through unaryCommandFactory when ButtonCalculatorUnary button is pressed`(
            button: ButtonCalculatorUnary
        ) {
            // Prepare the expected CommandEnterControl
            val expectedCommand = mockk<CommandApplySign>()

            // Mock the `create` method for CommandFactoryArithmetic to return the expected command
            every { unaryFactory.create(button) } returns expectedCommand

            // Create the action to simulate the button press
            val action = CalculatorAction.ButtonPressed(button)

            // Act: Call the method
            val command = factory.createCommand(action)

            // Assert: Ensure the returned command is the one expected
            command shouldBe expectedCommand

            // Verify that `create` was called with the correct argument
            verify { unaryFactory.create(button) }
        }


        @ParameterizedTest
        @MethodSource("provideControls")
        fun `should create corresponding control command through controlCommandFactory when ButtonCalculatorControl button is pressed`(
            button: ButtonCalculatorControl
        ) {
            // Prepare the expected CommandEnterControl
            val expectedCommand = mockk<CommandHandlerControl>()

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
        fun `should create CommandHandlerControl through numberCommandFactory when ButtonCalculatorNumber button is pressed`(
            button: ButtonCalculatorNumber
        ) {
            // Prepare the expected CommandHandlerControl
            val expectedCommand = mockk<CommandHandlerControl>()

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