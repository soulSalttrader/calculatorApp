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

## Features:

1. **Element**
2. **Button**

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
