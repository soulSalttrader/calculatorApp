# Project Title
Android Calculator App in Kotlin.

---

## Description
A straightforward Android application developed with Kotlin, designed as a simple yet functional calculator.
The app mimics the look and behavior of the iPhone calculator, offering a familiar and intuitive experience.

---

## Technologies Used
1. Kotlin: Main programming language.
2. Android Studio: IDE for development.
3. MVVM: Architecture pattern.
4. Jetpack Compose: UI toolkit for declarative UI design.

---

## Calculator Operations
1. Addition – Adds two numbers. The result is shown after pressing "=" or entering another operation.
2. Subtraction – Subtracts one number from another. The result appears after "=" or the next operation. 
3. Multiplication – Multiplies two numbers. The result is displayed after "=" or another operation. 
4. Division – Divides one number by another. The result shows after "=" or continuing with another operation. 
5. Repeatable Equals – Repeats the last operation when "=" is pressed again. 
6. Change Sign – Switches a number between positive and negative.
7. Decimal Support – Allows operations with decimal values. 
8. Percentage – Calculates a percentage of a given number. 
9. All Clear – Resets the calculator to default settings. 
10. Clear – Clears the last entered number or operation.

---

## Features:

1. **Element**
2. **Button**
3. **Display**
4. **Row**
5. **Engine**
6. **Command**

---

### 1. **Element**

The **Element** introduces a standardized approach to define UI components like buttons, displays, rows, and more. It provides modular, flexible interfaces to structure the UI and its behaviors.

#### Key Interfaces in the Element:

- **Element**: The core interface for all UI elements. It defines properties such as background color, text color, and category.
- **ElementCategory**: Categorizes UI elements (e.g., buttons, displays), grouping them by functionality.
- **ElementCategoryStyle**: Specifies the style of an element within a category (e.g., button styles, display styles).
- **ElementColorStyle**: Defines color schemes for UI elements (e.g., background and text colors).
- **ElementLayout**: Defines properties for positioning and styling elements, such as alignment, size, and shape.
- **ElementData**: Combines both the `Element` and its `ElementLayout` into a single object, making it easy to manage both behavior and layout.

#### Key Benefits of the Element System:

- **Modularity**: Swap or customize elements without breaking the system.
- **Flexibility**: Define custom styles and categories, enabling highly adaptable UI components.
- **Reusability**: Reuse elements across the project to improve code efficiency.

---

### 2. **Button**

The **Button** implementation extends the **Element**, offering reusable and customizable buttons. These buttons are organized into categories, each with its default style. However, you have the flexibility to individually style any button within a category, giving you fine control over their appearance and behavior.

#### Key Concepts of Button:

- **Symbol Interface**: Each button has a `symbol` property, which can either be a text label (e.g., `"+"`, `"C"`) or an icon (using a drawable resource ID). This `symbol` provides a way to identify and categorize buttons, allowing for consistent labeling across the UI.

    - The `Symbol` interface defines two properties:
        - `label`: A string that represents the button's label (e.g., `"AC"`, `"+"`, `"3"`).
        - `iconRes`: An optional integer representing the resource ID of the button's icon (e.g., a drawable).

- **SymbolButton Enum**: The `SymbolButton` enum class is an implementation of the `Symbol` interface that provides a predefined set of symbols for different categories of buttons (e.g., control buttons like `AC`, arithmetic buttons like `+`, `-`, etc.). Each `SymbolButton` has a label and, optionally, an icon.

- **Button Categories**: Buttons are categorized into types such as `Arithmetic`, `Control`, and `Number`. Each button category has its own unique style and behavior. Buttons can easily be extended by adding more categories (e.g., **Goniometric**, **Exponential**, etc.) for future functionality.

- **ButtonData**: The `ButtonData` class binds a button with its layout and style. It extends the `ElementData` interface, encapsulating both the button and its layout properties.

#### Example for Arithmetic Operations:

```kotlin
sealed class ButtonCalculatorArithmetic(override val symbol: Symbol) : Button {

    data object Addition : ButtonCalculatorArithmetic(SymbolButton.ADDITION)
    data object Subtraction : ButtonCalculatorArithmetic(SymbolButton.SUBTRACTION)
    data object Multiplication : ButtonCalculatorArithmetic(SymbolButton.MULTIPLICATION)
    data object Division : ButtonCalculatorArithmetic(SymbolButton.DIVISION)
    data object Equals : ButtonCalculatorArithmetic(SymbolButton.EQUALS)

    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Arithmetic
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}
```

#### Handling Buttons with Unique Styles within a Category:

In some cases, a button may belong to a particular category but needs a different style, such as a unique background or text color. To handle such cases, you can create specialized styles for specific buttons within a category.

For example, if you have a **Control** button category, but you want a button like `Decimal` to have a different color scheme than the default control buttons, you can define a custom style for that button while keeping it in the **Control** category. Here’s an example:

```kotlin
class ButtonCategoryStyleControl(
    baseStyle: ElementColorStyle,
    decimalStyle: ElementColorStyle? = null,
) : ElementCategoryStyleBase<ElementColorStyle>(
    baseStyle,
    mapOf(
        SymbolButton.DECIMAL.label to (decimalStyle ?: baseStyle) // Decimal button is categorized as a control button but should use number button color scheme
    )
)
```
#### Creating Button Styles:

Button styles can be created using the ButtonCategoryStyleBuilder, which allows you to define how buttons in different categories (like Arithmetic, Control, Number) should look. Here’s an example of creating a set of styles:

```kotlin
object StylesButton {

    val iButtonStyle = ButtonCategoryStyleBuilder()
        .arithmeticStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White),
        )
        .controlStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx),
            decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
        )
        .numberStyle(
            baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White)
        )
        .build()
}
```

---

### 3. **Display**

The **Display** implementation extends the **Element**,  offering customizable displays for different purposes. For the first version of the project, only the Input display will be fully developed and implemented. The displays are categorized into types such as **Input**, **Result**, **Error**, **History**, and **Memory**, each with its own default style. However, all display types beyond **Input** are considered concepts and are planned for future updates. Similar to buttons, displays can be individually styled, giving you fine control over their appearance and behavior.

#### Key Concepts of Display:

- **Display Interface**: The `Display` interface defines the basic properties and methods that every display element should have. It extends the `Element` interface, providing a standard structure for displays, which can be used across various categories and styles.

- **Display Categories**: Displays are organized into categories, such as `Input`, `Result`, `Error`, `History`, and `Memory`. For the first version, only the Input category will be implemented and fully functional. The remaining categories are considered concepts, with potential future development.

- **DisplayData**: The `DisplayData` class binds a display element with its layout and style. It extends the `ElementData` interface, encapsulating both the display and its layout properties.

- **Display Category Styles**: Just like buttons, displays can be styled individually within a category. For example, the **Input** category may have different styles for a **Scientific** input display compared to a regular **Standard** input display.

#### Example of Display Input Category:

```kotlin
sealed class DisplayCalculatorInput : Display {

    data object Standard : DisplayCalculatorInput()

    @ConceptClass
    data object Scientific : DisplayCalculatorInput()

    override fun getCategory(): ElementCategory<ElementColorStyle> = DisplayCategory.Input
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[this::class.simpleName] ?: categoryStyle.baseStyle
    }
}
```

#### Additional Concepts:

Handling different display categories, creating display styles, and defining DisplayData follow the same principles described in previous chapters (e.g., Button, Element). The DisplayCategoryStyleBuilder allows customization of styles for different display categories, maintaining consistency with other elements.

---

### 4. **Row**

The **Row** implementation extends the **Element**, providing a way to group multiple buttons into structured rows. Each row contains a predefined list of buttons and can be categorized for different layouts and styles.

#### Key Concepts of Row:

- **Row Interface**:  
  The `Row` interface defines the basic structure for a row of buttons. Each row holds a list of `ButtonData` elements, allowing for flexible button grouping and arrangement.

- **Row Categories**:  
  Rows are categorized to support different layouts. The initial implementation focuses on `Standard` rows, but additional row categories like `Scientific` may be introduced in future updates.

- **RowData**:  
  The `RowData` class binds a row with its layout and style. It extends the `ElementData` interface, encapsulating both the row and its layout properties.

#### Example of Standard Row Implementation:

```kotlin
interface Row : Element<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementColorStyle> {
    val buttons: List<ButtonData>
}

sealed class RowCalculatorStandard(override val buttons: List<ButtonData>) : Row {

    class Standard1(override val buttons: List<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard2(override val buttons: List<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard3(override val buttons: List<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard4(override val buttons: List<ButtonData>) : RowCalculatorStandard(buttons)
    class Standard5(override val buttons: List<ButtonData>) : RowCalculatorStandard(buttons)

    override fun getCategory(): ElementCategory<ElementColorStyle> = RowCategory.Standard
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor

    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")

        return categoryStyle.specificStyles[this::class.simpleName] ?: categoryStyle.baseStyle
    }
}
```

---

### 5. **Engine**

The **Engine** is responsible for handling the core logic of the calculator, including mathematical computations and operation execution. It does not directly manage UI states but instead focuses on the business logic of calculations. The engine consists of two primary components:

- **EngineMath**: Handles numerical operations such as arithmetic calculations, sign inversion, and percentage conversion.
- **EngineState**: Manages the calculator state, including user input handling and operation execution.

#### Key Concepts of Engine:

- **Engine Interface**:  
  The `Engine` interface serves as the base for all engine-related components.

- **EngineMath Interface**:  
  Defines core mathematical operations, including:
    - `applySign(number: Double)`: Inverts the sign of a number.
    - `applySign(number: Int)`: Inverts the sign of a number.
    - `applyPercentage(number: Double)`: Converts a number into a percentage.
    - `applyArithmetic(left: Double, right: Double, operation: ButtonCalculatorArithmetic)`: Performs arithmetic operations such as addition, subtraction, multiplication, and division.

  **Implementation:**
  ```kotlin
  class EngineMathStandard : EngineMath {

    override fun applySign(number: Double): Double = -number
    override fun applySign(number: Int): Int = -number

    override fun applyPercent(
        operandLeft: Double?,
        operator: ButtonCalculatorArithmetic?,
        operandRight: Double
    ): Double {
        return when (operator) {
            ButtonCalculatorArithmetic.Addition,
            ButtonCalculatorArithmetic.Subtraction -> (operandLeft ?: 1.0) * (operandRight / 100)

            ButtonCalculatorArithmetic.Multiplication,
            ButtonCalculatorArithmetic.Division -> operandRight / 100

            else -> operandRight / 100
        }
    }

    override fun applyArithmetic(
        operandLeft: Double,
        operator: ButtonCalculatorArithmetic,
        operandRight: Double,
    ): Double {

        return  when (operator) {
            is ButtonCalculatorArithmetic.Addition -> operandLeft + operandRight
            is ButtonCalculatorArithmetic.Subtraction -> operandLeft - operandRight
            is ButtonCalculatorArithmetic.Multiplication -> operandLeft * operandRight
            is ButtonCalculatorArithmetic.Division -> safeDivide(operandLeft, operandRight)
            else -> throw IllegalArgumentException("Unknown operation.")
        }
    }

    private fun safeDivide(operandLeft: Double, operandRight: Double) = if (operandRight != 0.0) operandLeft / operandRight else Double.NaN
  }
  ```
- **EngineState Interface**:  
  Manages internal calculator state transitions, such as updating numbers, performing operations, and handling clear actions. This interface focuses on ensuring that the correct state is maintained as operations are performed:
    - `handleArithmetic`(state, arithmetic): Manages state updates and applies the selected operation.
    - `enterArithmetic(state, arithmetic)`: Updates the state when an arithmetic operation is entered.
    - `applyArithmetic`(state): Applies the pending arithmetic operation.
    - `applyEquals`(state): Calculates the result of the current expression.
    - `enterNumber(state, number)`: Handles number input.
    - `enterDecimal(state)`: Adds a decimal point.
    - `applyClear(state)`: Clears the current input.
    - `applyClearAll(state)`: Resets the calculator state.
    - `applySign`(state): Toggles the sign of the current number.
    - `applyPercent`(state): Converts the current number into a percentage.

  **Implementation**:
  ```kotlin
  typealias Validator<T> = (T) -> Boolean
  
  class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

  override fun handleArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState {
      return state.modifyWith(
          { hasNaN(state) } to { this },
          { state.activeButton == ButtonCalculatorArithmetic.Equals } to { state.copy(operator = arithmetic, operandRight = "", operand = null) },
          { hasOperatorAndOperandRight(state) } to {
              val newState = applyArithmetic(state)
              enterArithmetic(newState, arithmetic)
          },
          { true } to { enterArithmetic(state, arithmetic) }
      )
  }

  override fun applyArithmetic(state: CalculatorState): CalculatorState {
      return state.modifyWith(
          { hasInvalidInput(state) } to { this },
          { true } to {
              val operandLeft = state.operandLeft.toDouble()
              val operandRight = state.operandRight.toDouble()

              if (operandLeft.isNaN() || operandRight.isNaN()) return@to state

              val operation = state.operator as ButtonCalculatorArithmetic
              val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

              state.copy(
                  operandRight = result.toString(),
                  operandLeft = "",
                  operator = null
              )
          }
      )
  }

  override fun enterArithmetic(state: CalculatorState, arithmetic: ButtonCalculatorArithmetic): CalculatorState {
      return state.modifyWith(
          { state.operator != null } to { state.copy(operator = arithmetic) },
          { state.operandRight.isNotBlank() } to { state.copy(operandLeft = state.operandRight, operandRight = "", operator = arithmetic) },
      )
  }

  override fun applyEquals(state: CalculatorState): CalculatorState {
      return state.modifyWith(
          { state.operandLeft.toDoubleOrNull() == null } to { this },
          { state.operandRight.toDoubleOrNull() == null && state.operand == null } to { this },
          { state.operator !is ButtonCalculatorArithmetic } to { this },
          { true } to {
              val operandLeft = state.operandLeft.toDouble()
              val operandRight = state.operand?.toDoubleOrNull() ?: state.operandRight.toDouble()

              if (operandLeft.isNaN() || operandRight.isNaN()) return@to state

              val operation = state.operator as ButtonCalculatorArithmetic
              val result = engineMath.applyArithmetic(operandLeft, operation, operandRight)

              state.copy(
                  operandRight = result.toString(),
                  operandLeft = result.toString(),
                  operand = operandRight.toString() // Save the last entered number
              )
          }
      )
  }

  override fun enterNumber(state: CalculatorState, number: Int): CalculatorState {
      return state.modifyWith(
          { hasNaN(state) } to { this },
          { state.operandRight == SymbolButton.ZERO.label } to { copy(operandRight = number.toString()) },
          { state.operandRight.length >= MAX_NUM_LENGTH } to { this },
          { true } to { copy(operandRight = operandRight + number) }
      )
  }

  override fun enterDecimal(state: CalculatorState): CalculatorState {
      return state.modifyWith(
          { hasNaN(state) } to { this },
          { !state.operandRight.contains(".") && state.operandRight.isNotBlank() } to { state.copy(operandRight = state.operandRight + ".") },
          { !state.operandRight.contains(".") && state.operandRight.isBlank() } to { state.copy(operandRight = SymbolButton.ZERO.label + ".") }
      )
  }

  override fun applyClearAll(state: CalculatorState): CalculatorState = CalculatorState()

  override fun applyClear(state: CalculatorState): CalculatorState {
      return state.modifyWith(
          { hasNaN(state) } to { applyClearAll(state) },
          { !state.operand.isNullOrBlank() } to { applyClearAll(state) },
          { state.operator != null } to { copy(operandRight = state.operandLeft, operandLeft = "", operator = null) },
          { state.operandRight.isNotBlank() } to { copy(operandRight = SymbolButton.ZERO.label, operandLeft = "") },
      )
  }

  override fun applySign(state: CalculatorState): CalculatorState {
      return state.modifyWith(
          { hasNaN(state) } to { this },
          { isDefaultOrEmpty(state) } to { state.copy(operandRight = "-" + SymbolButton.ZERO.label) },
          { state.operandRight.toIntOrNull() != null } to {
              val intNumber = state.operandRight.toInt()
              val result = engineMath.applySign(intNumber).toString()

              state.copy(operandRight = result)
          },
          { true } to {
              val doubleNumber = state.operandRight.toDouble()
              val result = engineMath.applySign(doubleNumber).toString()

              state.copy(operandRight = result)
          }
      )
  }

  override fun applyPercent(state: CalculatorState): CalculatorState {
      return state.modifyWith(
          { hasNaN(state) } to { this },
          { state.operandRight.toDoubleOrNull() == null } to { this },
          { true } to {
              val result = engineMath.applyPercent(
                  state.operandLeft.toDoubleOrNull(),
                  state.operator as? ButtonCalculatorArithmetic,
                  state.operandRight.toDouble()
              )

              state.copy(operandRight = result.toString())
          }
      )
  }

  val hasNaN: Validator<CalculatorState> = { it.operandRight == "NaN" || it.operandLeft == "NaN" }
  val hasOperatorAndOperandRight: Validator<CalculatorState> = { it.operator != null && it.operandRight.isNotBlank() }
  val hasInvalidInput: Validator<CalculatorState> = {
      it.operandLeft.toDoubleOrNull() == null ||
      it.operandRight.toDoubleOrNull() == null && it.operand == null ||
      it.operator !is ButtonCalculatorArithmetic
  }
  val isDefaultOrEmpty: Validator<CalculatorState> = { it.operandRight == SymbolButton.ZERO.label || it.operandRight.isEmpty() }
  }
  ```
- **CalculatorState**:
  Represents the state of the calculator, which includes the current number, previous number, the active operation, and the active button label. This state is crucial for making calculations based on user input.
    - `operandRight`: The number currently being entered.
    - `operator`: The currently selected arithmetic operation.
    - `operandLeft`: The number stored for an operation.
    - `operand`: Holds intermediate values, including for repeatable equals.
    - `activeButton`: The label of the active button.

  **Implementation**:
  ```kotlin
  data class CalculatorState(
    val operandLeft: String = "",
    val operator: Button? = null,
    val operandRight: String = SymbolButton.ZERO.label,
    val operand: String? = null,
    val activeButton: Button? = null,
  ) {
      fun modifyWith(vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>): CalculatorState {
          for ((condition, action) in transformations) {
            if (condition()) return action()
          }
          return this
      }
  }
  ```

---

### 6. Command

- The **Command** pattern is used to encapsulate calculator actions into executable objects. Each command represents a specific operation and allows flexible management of calculator behavior. Commands are executed using the execute method, which takes the current CalculatorState and returns an updated state.
- The **CommandFactory** is responsible for creating commands based on user actions. It ensures that the correct command is instantiated for each button press, promoting separation of concerns and simplifying state management.

#### Key Concepts of Commands:

- **Command Interface**:
  The `Command` interface defines the structure for all commands:
  - `CommandApplyArithmetic`: Executes an arithmetic operation.
  - `CommandApplyEquals`: Finalizes the current operation and displays the result.
  - `CommandApplyClear` and `CommandApplyClearAll`: Clear the current input or reset the entire state.
  - `CommandApplyPercent`: Converts the current value into a percentage.
  - `CommandApplySign`: Toggles the sign of the current number.
  - `CommandEnterNumber`: Handles number input.
  - `CommandEnterDecimal`: Appends a decimal point.
  - `CommandEnterArithmetic`: Sets the current operation.

  **Implementations**:

  ```kotlin
  class CommandApplyArithmetic(private val engine: EngineState) : Command {
      override fun execute(state: CalculatorState): CalculatorState {
          return engine.applyArithmetic(state)
      }
  }

   class CommandEnterNumber(
       private val engine: EngineState,
       private val number: Int,
   ) : Command {
       override fun execute(state: CalculatorState): CalculatorState {
           return engine.enterNumber(state, number)
       }
   }
  ```

- **Command Factory Interface**:
  The `CommandFacgtory` is a base interface for creating commands:
  - `CommandFactoryControl`: Creates commands for control buttons like clear, percent, and sign toggle.
  - `CommandFactoryNumber`: Creates number input commands.
  - `CommandFactoryArithmetic`: Creates commands for arithmetic operations.
  - `CommandFactoryStandard`: Serves as the primary factory, delegating to sub-factories based on the button type.

  **Implementations**:
  Different factory implementations handle specific button types:

  ```kotlin
   class CommandFactoryControl(
       private val engineState: EngineState,
   ) : CommandFactorySub<ButtonCalculatorControl> {
       override fun create(button: ButtonCalculatorControl): Command {
           return when (button) {
               is ButtonCalculatorControl.Decimal -> CommandEnterDecimal(engineState)
               is ButtonCalculatorControl.AllClear -> CommandApplyClearAll(engineState)
               is ButtonCalculatorControl.Clear -> CommandApplyClear(engineState)
               is ButtonCalculatorControl.Percentage -> CommandApplyPercent(engineState)
               is ButtonCalculatorControl.Sign -> CommandApplySign(engineState)
           }
       }
   }
   
   class CommandFactoryStandard(
       private val arithmeticCommandFactory: CommandFactoryArithmetic,
       private val controlCommandFactory: CommandFactoryControl,
       private val numberCommandFactory: CommandFactoryNumber,
   ) : CommandFactory {
       override fun createCommand(action: CalculatorAction): Command {
           return when (action) {
               is CalculatorAction.ButtonPressed -> handleButtonPressed(action.button)
           }
       }
   
       private fun handleButtonPressed(button: Button): Command {
           return when (button) {
               is ButtonCalculatorNumber -> numberCommandFactory.create(button)
               is ButtonCalculatorArithmetic -> arithmeticCommandFactory.create(button)
               is ButtonCalculatorControl -> controlCommandFactory.create(button)
               else -> throw IllegalArgumentException("Unknown button.")
           }
       }
   }
  ```

The CommandFactory simplifies the process of translating user interactions into executable actions while maintaining modularity and readability.

___

## Development Practices:

### **Concept Annotations:**

The **Concept Annotations** feature is designed to help manage experimental or under development ideas within the project code. These annotations mark classes, methods, or other elements that are part of concepts still being explored, with the intention to separate them from the production code. This mechanism helps track potential future work and experimental features that may evolve, change, or even be removed in future versions of the project.

Unlike features that are actively being developed and are part of the core functionality, Concept Annotations are used specifically to isolate ideas or concepts that might be realized in future updates but are not part of the current product. These concepts should be used with caution, as they are not finalized and could change or be removed entirely.

#### Key Concepts of Concept Annotations:

- **Concept Interface**: The `Concept` interface is used to track concepts that are currently under development. These concepts are experimental and subject to change.

#### ConceptClass Annotation:

- The @ConceptClass annotation is used to mark a class as a concept that is under development and may change significantly or be removed without prior notice. It signals to developers that the class is part of an experimental or evolving feature.

```kotlin
@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "This is a concept under development and may change significantly or be removed without prior notice.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.CLASS)
annotation class ConceptClass
```

#### ConceptMethod Annotation:

- The @ConceptMethod annotation marks a method that is part of an under development concept and may change significantly or be removed without prior notice. This helps track methods that are placeholders or are being designed for future functionality.

```kotlin
@RequiresOptIn(level = RequiresOptIn.Level.ERROR, message = "This is a concept under development and may change significantly or be removed without prior notice.")
@Retention(AnnotationRetention.BINARY)
@Target(AnnotationTarget.FUNCTION)
annotation class ConceptMethod
```
---
