# Calculator App

## ℹ️ About The Project
A straightforward Android application developed with Kotlin, designed as a simple yet functional calculator.
The app mimics the look and behavior of the iPhone calculator, offering a familiar and intuitive experience.

---
<br>

## 🛠️ Built With
- **Kotlin** — Concise and modern language for Android development.
- **MVVM** — Scalable architecture for clear separation of logic and UI.
- **Jetpack Compose** — Declarative UI framework for fast and flexible design.
- **Android Studio** — Powerful IDE for coding, building, and debugging.

---
<br>

## 🔎 Calculator Overview
The calculator supports a full suite of intuitive, responsive operations designed for real-time math evaluation. Here's what it can do out of the box:

##### Core Operations:
- ➕ Addition
- ➖ Subtraction
- ✖️ Multiplication
- ➗ Division

##### Additional Operations:
- 🔀 Context-aware percentage
- 🔁 Repeatable Equals
- 0️⃣ Decimal Support
- 🔄 Change Sign

##### State Control:
- 🔴 All Clear
- 🚫 Clear

---
<br>

## ✅ Roadmap
- [x] 1. Element
- [x] 2. Button
- [x] 3. Display
- [x] 4. Row
- [x] 5. Engine
- [x] 6. Command
- [x] 7. ASTNode
- [ ] 8. ViewModel
- [ ] 9. UI

---
<br>

## 🏛️ Architecture

### ⚛️ 1. **Element**

The **Element** introduces a standardized approach to define UI components like buttons, displays, rows, and more. It provides modular, flexible interfaces to structure the UI and its behaviors.

- **`Element`**: The core interface for all UI elements. It defines properties such as background color, text color, and category.
- **`ElementCategory`**: Categorizes UI elements (e.g., buttons, displays), grouping them by functionality.
- **`ElementCategoryStyle`**: Specifies the style of an element within a category (e.g., button styles, display styles).
- **`ElementColorStyle`**: Defines color schemes for UI elements (e.g., background and text colors).
- **`ElementLayout`**: Defines properties for positioning and styling elements, such as alignment, size, and shape.
- **`ElementData`**: Combines both the `Element` and its `ElementLayout` into a single object, making it easy to manage both behavior and layout.

#### Key Benefits of the Element System:

- **Modularity**: Swap or customize elements without breaking the system.
- **Flexibility**: Define custom styles and categories, enabling highly adaptable UI components.
- **Reusability**: Reuse elements across the project to improve code efficiency.

---
<br>

### 🔘 2. **Button**

The **Button** implementation extends the **Element**, offering reusable and customizable buttons. These buttons are organized into categories, each with its default style. However, you have the flexibility to individually style any button within a category, giving you fine control over their appearance and behavior.

#### 2.1 **Symbol Interface**
Each button has a `symbol` property, which can either be a text label (e.g., `"+"`, `"C"`) or an icon (using a drawable resource ID). This `symbol` provides a way to identify and categorize buttons, allowing for consistent labeling across the UI.

- The `Symbol` interface defines two properties:
  - `label`: A string that represents the button's label (e.g., `"AC"`, `"+"`, `"3"`).
  - `iconRes`: An optional integer representing the resource ID of the button's icon (e.g., a drawable).

#### 2.2 **SymbolButton Enum**
The `SymbolButton` enum class is an implementation of the `Symbol` interface that provides a predefined set of symbols for different categories of buttons (e.g., control buttons like `AC`, binary buttons like `+`, `-`, etc.). Each `SymbolButton` has a label and, optionally, an icon.

#### 2.3 **Button Categories**
Buttons are categorized into types such as `Binary`, `Unary`, `Control`, `Number`, and `Parenthesis`. Each button category has its own unique style and behavior. Buttons can easily be extended by introducing new implementations as subclasses of existing categories (e.g., Binary, Unary, Control, Number, Parenthesis) for future functionality:

```kotlin
sealed class ButtonCalculatorBinaryAdvanced(
    override val symbol: Symbol
) : ButtonCalculatorBinary(symbol) {

    data object Modulo : ButtonCalculatorBinaryAdvanced(SymbolButton.MODULO)
    data object Power : ButtonCalculatorBinaryAdvanced(SymbolButton.POWER)
}
```

#### 2.4 **ButtonData**
The `ButtonData` class binds a button with its layout and style. It extends the `ElementData` interface, encapsulating both the button and its layout properties.

#### 2.5 Example for Binary buttons

```kotlin
sealed class ButtonCalculatorBinary(override val symbol: Symbol) : Button {

    data object Addition : ButtonCalculatorBinary(SymbolButton.ADDITION)
    data object Subtraction : ButtonCalculatorBinary(SymbolButton.SUBTRACTION)
    data object Multiplication : ButtonCalculatorBinary(SymbolButton.MULTIPLICATION)
    data object Division : ButtonCalculatorBinary(SymbolButton.DIVISION)
    
    override fun getCategory(): ElementCategory<ElementColorStyle> = ButtonCategory.Binary
    override fun getBackgroundColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).backgroundColor
    override fun getTextColor(style: ElementCategoryStyleCollection<ElementColorStyle>): Color = getStyle(style).textColor
    
    private fun getStyle(style: ElementCategoryStyleCollection<ElementColorStyle>): ElementColorStyle {
        val categoryStyle = style.categories[getCategory()]
            ?: throw IllegalArgumentException("Category '${getCategory()}' not found.")
    
        return categoryStyle.specificStyles[symbol.label] ?: categoryStyle.baseStyle
    }
}
```

#### 2.6 **Handling Buttons with Unique Styles within a Category**:

In some cases, a button may belong to a particular category but needs a different style, such as a unique background or text color. To handle such cases, you can create specialized styles for specific buttons within a category.

For example, if you have a **Control** button category, but you want a button like `Decimal` to have a different color scheme than the default control buttons, you can define a custom style for that button while keeping it in the **Control** category. Here’s an example:

```kotlin
class ButtonCategoryStyleControl(
    baseStyle: ElementColorStyle,
    decimalStyle: ElementColorStyle? = null,
    equalsStyle: ElementColorStyle? = null,
) : ElementCategoryStyleBase<ElementColorStyle>(
    baseStyle,
    mapOf(
        SymbolButton.DECIMAL.label to (decimalStyle ?: baseStyle), // Decimal button is categorized as a control button but should use number button color scheme
        SymbolButton.EQUALS.label to (equalsStyle ?: baseStyle) // Equals button is categorized as a control button but should use binary button color scheme
    )
)
```

#### 2.7 Creating Button Styles:

Button styles can be created using the ButtonCategoryStyleBuilder, which allows you to define how buttons in different categories (like Binary, Unary, Control, Number, Parenthesis) should look. Here’s an example of creating a set of styles:

```kotlin
object StylesButton {

  val iButtonStyle = ButtonCategoryStyleBuilder()
      .binaryStyle(
          baseStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White),
      )
      .unaryStyle(
          baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx),
      )
      .controlStyle(
          baseStyle = ElementColorStyleImpl(backgroundColor = SilverGrey, textColor = Onyx),
          decimalStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
          equalsStyle = ElementColorStyleImpl(backgroundColor = VividGamboge, textColor = White),
      )
      .numberStyle(
          baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
      )
      .parenthesisStyle(
          baseStyle = ElementColorStyleImpl(backgroundColor = Onyx, textColor = White),
      )
      .build()
}
```

---
<br>

### 🖥️ 3. **Display**

The **Display** implementation extends the **Element**,  offering customizable displays for different purposes. For the first version of the project, only the Input display will be fully developed and implemented. The displays are categorized into types such as **Input**, **Result**, **Error**, **History**, and **Memory**, each with its own default style. However, all display types beyond **Input** are considered concepts and are planned for future updates. Similar to buttons, displays can be individually styled, giving you fine control over their appearance and behavior.

#### 3.1 **Display Interface**
The `Display` interface defines the basic properties and methods that every display element should have. It extends the `Element` interface, providing a standard structure for displays, which can be used across various categories and styles.

#### 3.2 **Display Categories**
Displays are organized into categories, such as `Input`, `Result`, `Error`, `History`, and `Memory`. For the first version, only the `Input` category will be implemented and fully functional. The remaining categories are considered concepts, with potential future development.

#### 3.3 **DisplayData**
The `DisplayData` class binds a display element with its layout and style. It extends the `ElementData` interface, encapsulating both the display and its layout properties.

#### 3.4 **Display Category Styles**
Just like buttons, displays can be styled individually within a category. For example, the **Input** category may have different styles for a **Scientific** input display compared to a regular **Standard** input display.

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
<br>

---
<br>

### ➡️ 4. **Row**

The **Row** implementation extends the **Element**, providing a way to group multiple buttons into structured rows. Each row contains a predefined list of buttons and can be categorized for different layouts and styles.

#### 4.1 **Row Interface**

The `Row` interface defines the basic structure for a row of buttons. Each row holds a list of `ButtonData` elements, allowing for flexible button grouping and arrangement.

```kotlin
interface Row : Element<ElementCategory<ElementColorStyle>, ElementCategoryStyleCollection<ElementColorStyle>, ElementColorStyle> {
    val buttons: List<ButtonData>
}
```

#### 4.2 **Row Categories**

Rows are categorized to support different layouts. The initial implementation focuses on `Standard` rows, but additional row categories like `Scientific` may be introduced in future updates.

#### 4.3 **RowData**

The `RowData` class binds a row with its layout and style. It extends the `ElementData` interface, encapsulating both the row and its layout properties.

#### 4.4 Example of Standard Row Implementation

```kotlin
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
<br>

### 🚀 5. **Engine**

The **Engine** is responsible for handling the core logic of the calculator, including mathematical computations and operation execution. It does not directly manage UI states but instead focuses on the business logic of calculations. The engine consists of three primary components:

- **EngineMath**: Handles numerical operations such as arithmetic calculations, sign inversion, and percentage conversion.
- **EngineNode**: Handles expression evaluation by parsing tokens into an Abstract Syntax Tree (AST) and recursively computing the result using defined operator rules.
- **EngineState**: Manages the calculator state, including user input handling and operation execution.

#### 1️⃣ 5.1 **Engine Interface**
The `Engine` interface serves as the base for all engine-related components.

#### 2️⃣ 5.2 **EngineMath Interface**
Defines core mathematical operations, including:
- `evalBinary(leftOperand: Double, rightOperand: Double, operation: BinaryOperation)`: Evaluates Binary operations.
- `evalPercent(operand: Double, previousNumber: Double?, lastOperator: Operator?)`: Evaluates a percentage.
- `evalSign(operand: Double)`: Evaluates the sign of a number.

##### **Implementation:**
```kotlin
class EngineMathStandard : EngineMath {

  override fun evalBinary(
      leftOperand: Double,
      rightOperand: Double,
      operation: BinaryOperation,
  ): EvaluationResult {
      val normalizedLeft = EvaluationResult.DoubleResult(leftOperand)
      val normalizedRight = EvaluationResult.DoubleResult(rightOperand)
      val result = operation(normalizedLeft, normalizedRight)

      return EvaluationResult.normalizeResult(result.value.toDouble())
  }

  override fun evalPercent(
      operand: Double,
      previousNumber: Double?,
      lastOperator: Operator?
  ): EvaluationResult {
      val percentage = operand / 100

      return when (lastOperator) {
          OperatorBinary.Addition, OperatorBinary.Subtraction -> {
              val result = ((previousNumber ?: 1.0) * (percentage))
              EvaluationResult.normalizeResult(result)
          }

          OperatorBinary.Multiplication, OperatorBinary.Division -> {
              EvaluationResult.normalizeResult(percentage)
          }
          else -> EvaluationResult.normalizeResult(percentage)
      }
  }

  override fun evalSign(operand: Double): EvaluationResult {
      return EvaluationResult.normalizeResult(-operand)
  }
}
```
#### 3️⃣ 5.3 **EngineNode Interface**
The EngineNode component is responsible for evaluating an Abstract Syntax Tree (AST) generated from user input. It decouples expression evaluation from direct token parsing, promoting a modular and extensible architecture.

```kotlin
interface EngineNode : Engine {
  fun evaluate(astNode: ASTNode): EvaluationResult
}
```
##### Core Responsibilities:
- Converts a structured expression tree (AST) into a computed result.
- Delegates arithmetic operations to the EngineMath component.
- Recursively evaluates binary operations based on operator type.

##### Key Methods:
- `fun evaluate(astNode: ASTNode): EvaluationResult`: Traverses and evaluates an AST, returning a normalized result.

##### **Implementation**:
```kotlin
class EngineNodeStandard(private val engineMath: EngineMath) : EngineNode {

    override fun evaluate(astNode: ASTNode): EvaluationResult {
        return when (astNode) {
            is ASTNode.Number -> normalizeResult(astNode.value)
            is ASTNode.Binary -> evalBinaryExpression(astNode)
        }
    }
  
    private fun evalBinaryExpression(astNode: ASTNode.Binary): EvaluationResult {
        val left = evaluate(astNode.left).value.toDouble()
        val right = evaluate(astNode.right).value.toDouble()

        return when (astNode.operator) {
            is OperatorBinary.Addition -> engineMath.evalBinary(left, right) { l, r -> l + r }
            is OperatorBinary.Subtraction -> engineMath.evalBinary(left, right) { l, r -> l - r }
            is OperatorBinary.Multiplication -> engineMath.evalBinary(left, right) { l, r -> l * r }
            is OperatorBinary.Division -> engineMath.evalBinary(left, right) { l, r -> l / r }
        }
 }

//...

}
```

#### 4️⃣ 5.4 **EngineState Interface**
EngineState is responsible for managing calculator state transitions, ensuring correct updates as operations are performed.

```kotlin
interface EngineState : Engine {
  fun handleBinary(state: CalculatorState, binary: ButtonCalculatorBinary): CalculatorState
  fun handleUnary(state: CalculatorState, unary: ButtonCalculatorUnary): CalculatorState
  fun handleControl(state: CalculatorState, control: ButtonCalculatorControl): CalculatorState
  fun handleNumber(state: CalculatorState, number: ButtonCalculatorNumber): CalculatorState
}
```

##### Core Responsibilities:
- Handles numeric input, operations, and controls to maintain a consistent calculator state.
- Ensures correct state updates based on user interactions.

##### Key Methods:
- `handleBinary(state, binary)`: Applies the selected binary operation (e.g., Addition, Subtraction).
- `handleUnary(state, unary)`: Processes unary operations (e.g., Sign, Percentage).
- `handleControl(state, control)`: Handles control actions (e.g., Clear, Clear All).
- `handleNumber(state, number)`: Manages number input and state updates.

##### **Implementation**:
```kotlin
class EngineStateStandard(private val engineMath: EngineMath) : EngineState {

    override fun handleBinary(state: CalculatorState, binary: ButtonCalculatorBinary): CalculatorState {
        return state.modifyWith(
            { state.lastOperand == "NaN" || state.expression.contains("NaN") } to { this },
            { state.activeButton == ButtonCalculatorControl.Equals } to { state.copy(lastOperator = binary, lastOperand = "", lastResult = null) },
            { state.lastOperator != null && state.lastOperand.isNotBlank() } to {
            val newState = applyBinary(state)
            enterBinary(newState, binary)
            },
            { true } to { enterBinary(state, binary) }
        )
    }

    override fun handleUnary(state: CalculatorState, unary: ButtonCalculatorUnary): CalculatorState {
        return state.modifyWith(
            { true } to {
                when (unary) {
                    is ButtonCalculatorUnary.Sign -> applySign(state)
                    is ButtonCalculatorUnary.Percentage -> applyPercent(state)
                }
            }
        )
    }

    override fun handleControl(state: CalculatorState, control: ButtonCalculatorControl): CalculatorState {
        return state.modifyWith(
            { true } to {
                when (control) {
                    is ButtonCalculatorControl.AllClear -> applyClearAll()
                    is ButtonCalculatorControl.Clear -> applyClear(state)
                    is ButtonCalculatorControl.Decimal -> enterDecimal(state)
                    is ButtonCalculatorControl.Equals -> applyEquals(state)
                }
            }
        )
    }

    override fun handleNumber(state: CalculatorState, number: ButtonCalculatorNumber): CalculatorState {
        return state.modifyWith(
            { true } to {
                applyNumber(state, number)
            }
        )
    }
}
```

#### 🔵 5.4.1 **CalculatorState**
CalculatorState represents the current state of the calculator, maintaining essential data for computations, user interactions, and error handling.

##### **State properties**:
- `expression` – Stores the sequence of inputs for an ongoing operation.
- `lastOperand` – The number currently being entered.
- `lastResult` – The most recently computed result.
- `lastOperator` – The last selected arithmetic operation.
- `cachedOperand` – Holds intermediate values, including for repeatable equals (=).
- `activeButton` – Tracks the last pressed button.
- `isComputed` – Indicates whether the last action resulted in a computation (used to reset input).
- `hasError` – Flags if an error has occurred during a computation.
- `errorMessage` – Describes the error when hasError is true.

##### **State Modification with `modifyWith`**:
The `modifyWith` function conditionally applies transformations to CalculatorState based on a set of conditions.

##### - **Functionality**:
- Accepts a list of transformation pairs, where each:
  - Condition: A function returning Boolean that determines if the transformation should apply.
  - Action: A function that modifies CalculatorState when the condition is met.
- The first matching transformation is applied.
- If no conditions match, the state remains unchanged.
- If an exception occurs, the state is updated with an error message.

##### - **Parameters**:
- transformations – A vararg list of condition-action pairs.
- errorMessage (optional) – A message set if an exception occurs during modification.

##### - **Returns**:
- A new CalculatorState reflecting the applied transformation.
- The original state if no condition matches.
- An error state if an exception occurs.

##### **Implementation**:
```kotlin
data class CalculatorState(
  val expression: List<Token> = emptyList(),
  val lastOperand: String = SymbolButton.ZERO.label,
  val lastResult: String? = null,
  val lastOperator: Operator? = null,
  val cachedOperand: String? = null,
  val activeButton: Button? = null,
  val isComputed: Boolean = false,
  val hasError: Boolean = false,
  val errorMessage: String? = null,
) {
  fun modifyWith(
      vararg transformations: Pair<() -> Boolean, CalculatorState.() -> CalculatorState>,
      errorMessage: String? = null,
  ): CalculatorState {
      return try {
          transformations
              .firstOrNull { it.first() }
              ?.second?.invoke(this)
              ?: this
      } catch (e: Exception) {
          this.copy(hasError = true, errorMessage = errorMessage ?: e.message)
      }
  }
}
```

##### Why `modifyWith`?
- Improves readability – Avoids deeply nested if statements.
- Encapsulates state logic – Centralized modification logic improves maintainability.
- Gracefully handles errors – Ensures unexpected failures don’t break the application.

---
<br>

### 🫡 6. Command

- The **Command** pattern is used to encapsulate calculator actions into executable objects.
- The **CommandFactory** is responsible for creating commands based on user actions.

#### 6.1 **Command Interface**:
The `Command` interface represents an executable action in the calculator system.

```kotlin
interface Command {
    fun execute(state: CalculatorState): CalculatorState
}
```

#### **Implementations**:
The `CommandHandler` class executes commands by delegating to the EngineState, based on the provided button type.
```kotlin
data class CommandHandler<T : Button>(
    private val engineState: EngineState,
    private val button: T,
) : Command {

    override fun execute(state: CalculatorState): CalculatorState {
        return when (button) {
            is ButtonCalculatorBinary -> engineState.handleBinary(state, button)
            is ButtonCalculatorUnary -> engineState.handleUnary(state, button)
            is ButtonCalculatorControl -> engineState.handleControl(state, button)
            is ButtonCalculatorNumber -> engineState.handleNumber(state, button)
            else -> throw IllegalArgumentException("Invalid button: $button")
        }
    }
}
```

#### 6.2 **Command Factory Interface**:
The CommandFactory interface provides a mechanism to create commands dynamically based on user actions.

```kotlin
interface CommandFactory {
    fun createCommand(action: CalculatorAction): Command
}
```

#### **Implementations**:
`CommandFactoryStandard` serves as the concrete factory that creates CommandHandler instances based on button actions.
```kotlin
class CommandFactoryStandard(
    private val engineState: EngineState
) : CommandFactory {

    override fun createCommand(action: CalculatorAction): Command {
        return when (action) {
            is CalculatorAction.ButtonPressed -> CommandHandler(engineState, action.button)
        }
    }
}
```

The CommandFactoryStandard simplifies the process of translating user interactions into executable commands while ensuring modularity and readability. It dynamically generates CommandHandler instances based on the button type, allowing the calculator to process different operations efficiently.

___
<br>

### 🧶 7. **ASTNode**

#### 7.1 **Token**
Tokens represent parsed user inputs, categorized into numerical values, operators (binary or unary), or parentheses. These tokens serve as input to the parser.

  ```kotlin
  sealed interface Token {
     data class Number(val value: Double) : Token()
     data class Binary(val operator: OperatorBinary) : Token()
     data class Unary(val operator: OperatorUnary) : Token()
     data class Parenthesis(val type: OperatorParenthesis) : Token()
  }
  ```

#### 7.2 **Node**
ASTNodes represent the syntactic structure of an expression. Nodes are recursively evaluated to compute a result.

  ```kotlin
  sealed class ASTNode {
     data class Number(val value: Double) : ASTNode()
     data class Binary(val operator: OperatorBinary, val left: ASTNode, val right: ASTNode) : ASTNode()
  }
  ```

#### 7.3 Precedence
Precedence defines operator priority for parsing expressions. 
Each operator type is associated with a numeric level to resolve parsing order correctly.

  ```kotlin
  sealed class Precedence(val level: Int) {
      data object Lowest : Precedence(0)  // Default for unknown tokens
      data object Sum : Precedence(1)     // +, -
      data object Product : Precedence(2) // *, /
      data object Prefix : Precedence(3)  // -x, +x
      data object Suffix : Precedence(4)  // x!, x², %
      data object Power : Precedence(5)   // x^y
      data object Group : Precedence(6)   // (, )
  
      companion object {
          fun fromToken(token: Token): Precedence {
              return when (token) {
                  is Token.Binary -> when (token.operator) {
                      OperatorBinary.Addition,
                      OperatorBinary.Subtraction -> Sum
                      OperatorBinary.Multiplication,
                      OperatorBinary.Division -> Product
                  }
                  is Token.Unary -> when (token.operator) {
                      OperatorUnary.Prefix.Sign -> Prefix
                      OperatorUnary.Suffix.Percentage -> Suffix
                  }
                  is Token.Parenthesis -> Group
                  else -> Lowest
              }
          }
      }
  }
  ```

#### 7.4 Tokenizer
The TokenizerStandard converts a list of raw string tokens into structured Token objects. 
It detects numbers, binary operators, unary operators, and parentheses.

  ```kotlin
  class TokenizerStandard : Tokenizer {
  
      override fun tokenize(expression: List<String>): List<Token> {
          val tokens = mutableListOf<Token>()
  
          for (token in expression) {
              tokens.add(
                  when {
                      token.isNumber() -> Token.Number(token.toDouble())
                      token.isBinary() -> Token.Binary(token.toBinaryOperator())
                      token.isParenthesis() -> Token.Parenthesis(token.toParenthesisOperator())
                      token.isUnaryPrefix() || token.isUnarySuffix() -> Token.Unary(token.toUnaryOperator())
                      else -> throw IllegalArgumentException("Invalid token: $token")
                  }
              )
          }
  
          return tokens
      }
  }
  ```
- Design Note
  - Precedence levels ensure operators are evaluated in the correct order when building the AST.
  - TokenizerStandard separates lexical analysis from parsing, enabling modularity and easier testing.

#### 7.6 **Parser**
The Parser component converts a list of tokens into a tree of ASTNode objects. It handles operator precedence and builds nested nodes for binary operations.

  ```kotlin
  class ParserToken : Parser {
  
   override fun parse(tokens: List<Token>): ASTNode {
       val output = mutableListOf<ASTNode>()
       val operators = mutableListOf<Token>()
  
       tokens.forEach { token ->
           when (token) {
               is Token.Number -> output.add(ASTNode.Number(token.value))
               is Token.Binary -> handleBinaryOperator(token, output, operators)
               else -> throw IllegalArgumentException("Invalid token for: $token")
           }
       }
  
       while (operators.isNotEmpty()) { buildOperatorNode(output, operators.removeLast()) }
       require(output.size == 1) { "Invalid expression" }
  
       return output.single()
   }
  
  //...
  
  }
  ```
___
<br>

### 🪟 8. **ViewModel**
This calculator app is organized around a modular, testable, and state-driven architecture. It separates UI, business logic, and state management, leveraging Hilt for dependency injection and StateFlow for reactive state updates.

#### 8.1 Actions
CalculatorAction represents events triggered by user interactions.
Currently, the primary action is ButtonPressed, capturing the pressed button.

```kotlin
sealed class CalculatorAction {
    abstract val button: Button
    data class ButtonPressed(override val button: Button) : CalculatorAction()
}
```

#### 8.2 Action Handling
- CalculatorActionHandler is responsible for processing actions and updating state.
- Returns CalculatorActionHandlerData, which contains:
  - newState: the updated calculator state
  - shouldResetTextSize: optional UI behavior
- Standard implementation uses a CommandFactoryProvider to map buttons to commands:

```kotlin
class CalculatorActionHandlerStandard(
    private val factoryProvider: CommandFactoryProvider,
) : CalculatorActionHandler {
    override fun handleAction(action: CalculatorAction, state: CalculatorStateDomain): CalculatorActionHandlerData {
        require(action is CalculatorAction.ButtonPressed)
        val commandFactory = factoryProvider.getFactory(action.button.getCategory().toButtonCategoryHiltKey())
        val command = commandFactory.createCommand(action)
        val newState = command.execute(state).copy(activeButton = action.button)
        return CalculatorActionHandlerDataStandard(newState, action.shouldResetTextSize())
    }
}
```

#### 8.3 Modules and Dependency Injection
Hilt modules provide centralized, singleton instances of key components, like the action handler:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object ModuleCalculatorActionHandler {
    @Provides
    @Singleton
    fun provideCalculatorActionHandler(factoryProvider: CommandFactoryProvider): CalculatorActionHandler =
        CalculatorActionHandlerStandard(factoryProvider)
}
```

- Other modules organize the app into features, e.g., ModuleEngineMath, ModuleParser, ModuleState, and Node processing.
- Hilt annotations (@MapKey, @Singleton, etc.) are used to map button categories to command factories dynamically.

#### 8.4 Button Categories and Command Factories

##### 🟡 8.4.1 ButtonCategoryHiltKey
ButtonCategoryHiltKey enumerates high-level button types, used to map buttons to command factories via Hilt.

```kotlin
enum class ButtonCategoryHiltKey {
    BINARY,
    UNARY,
    CONTROL,
    NUMBER,
    PARENTHESIS
}
```
Mapping from element category:

```kotlin
fun ElementCategory<*>.toButtonCategoryHiltKey(): ButtonCategoryHiltKey = when (this) {
    ButtonCategory.Binary -> ButtonCategoryHiltKey.BINARY
    ButtonCategory.Unary -> ButtonCategoryHiltKey.UNARY
    ButtonCategory.Control -> ButtonCategoryHiltKey.CONTROL
    ButtonCategory.Number -> ButtonCategoryHiltKey.NUMBER
    ButtonCategory.Parenthesis -> ButtonCategoryHiltKey.PARENTHESIS
    else -> throw IllegalArgumentException("Invalid button category.")
}
```
- This mapping allows the action handler to select the appropriate command factory for each button press.

##### 🟡 8.4.2 Hilt MapKey for Command Factories
ButtonCategoryHiltMapKey is used as a map key annotation for multi-binding in Hilt:

```kotlin
@MapKey
@Retention(AnnotationRetention.RUNTIME)
annotation class ButtonCategoryHiltMapKey(val value: ButtonCategoryHiltKey)
```
- Multi-binding lets Hilt inject a map of factories, keyed by button category.

##### 🟡 8.4.3 Module for Command Factories
ModuleCalculatorCommandFactory provides category-specific CommandFactory instances using Hilt:

```kotlin
@Module
@InstallIn(SingletonComponent::class)
object ModuleCalculatorCommandFactory {

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.BINARY)
    fun provideBinaryCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.UNARY)
    fun provideUnaryCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.CONTROL)
    fun provideControlCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)

    @Provides
    @IntoMap
    @ButtonCategoryHiltMapKey(ButtonCategoryHiltKey.NUMBER)
    fun provideNumberCommandFactory(engineState: EngineState): CommandFactory = CommandFactoryStandard(engineState)
}
```

- Each provider creates a CommandFactoryStandard tied to the EngineState domain, ensuring commands operate on the correct calculator logic.
- Multi-binding allows the CalculatorActionHandler to dynamically select the appropriate factory for a button press based on its category.

##### 🟡 8.4.4 Design Notes

- Extensible: New button categories can be added simply by:
- Extending ButtonCategoryHiltKey
- Adding a Hilt provider in the module
- Decoupled: The mapping decouples UI button categories from command execution, enabling clean modularity and easier testing.
- Type-safe: The use of enums and Hilt map keys prevents runtime errors due to missing or invalid command factories.

#### 8.5 ViewModel 
CalculatorViewModel exposes reactive StateFlow objects for UI consumption and persists state across configuration changes using SavedStateHandle.

```kotlin
@HiltViewModel
class CalculatorViewModel @Inject constructor(
    private val actionHandler: CalculatorActionHandler,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    companion object {
        private const val STATE_CAL = "calculator_state"
        private const val STATE_UI = "ui_state"
        private const val ORIENTATION = "calculator_orientation"
    }

    val stateCal: StateFlow<CalculatorStateDomain> = savedStateHandle.getStateFlow(STATE_CAL, CalculatorStateDomain())
    val stateUi: StateFlow<CalculatorStateUI> = savedStateHandle.getStateFlow(STATE_UI, CalculatorStateUI.DEFAULT)
    val isLandscape: StateFlow<Boolean> = savedStateHandle.getStateFlow(ORIENTATION, false)

    private fun setState(newState: CalculatorStateDomain) {
        savedStateHandle[STATE_CAL] = newState
    }

    fun setOrientation() {
        val currentOrientation = isLandscape()
        savedStateHandle[ORIENTATION] = currentOrientation
    }

    private fun setStateUi(transform: (CalculatorStateUI) -> CalculatorStateUI) {
        savedStateHandle[STATE_UI] = transform(stateUi.value)
    }

    fun onAction(action: CalculatorAction) {
        val result = actionHandler.handleAction(action, stateCal.value)
        setState(result.newState)
    }
}
```
- Notes on SavedStateHandle:
  - Persists stateCal, stateUi, and orientation across process death and configuration changes.
  - Enables reactive updates via StateFlow, so UI components automatically reflect the latest state.
  - Can be extended to store additional UI or domain state, supporting future features like undo/redo or session persistence.

#### 8.6 Key Design Principles

- Separation of concerns: Actions, state, and commands are decoupled.
- Modularity: Features are organized into self-contained modules, supporting dependency injection.
- Reactive state: StateFlow ensures the UI reacts automatically to state changes.
- Extensibility: Adding new buttons or commands requires minimal changes, thanks to the factory-based action handler.
- Enhancement opportunities:
  - Extend SavedStateHandle usage to support richer UI state persistence (themes, memory functions).
  - Introduce undo/redo stacks or more granular state snapshots.
  - Add testing hooks for observing state transitions in unit and integration tests.

___
<br>

## 💻 Development Practices:

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
