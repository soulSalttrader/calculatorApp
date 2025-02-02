# Project Title
Android Calculator App in Kotlin.

## Description
A straightforward Android application developed with Kotlin, designed as a simple yet functional calculator. 
The app mimics the look and behavior of the iPhone calculator, offering a familiar and intuitive experience. 

## Technologies Used
1. Kotlin: Main programming language.
2. Android Studio: IDE for development.
3. MVVM: Architecture pattern.
4. Jetpack Compose: UI toolkit for declarative UI design.

## Features: 
1. Element Interfaces

### Element System

In order to better structure and define the components of the UI, we've introduced a new Element System. The Element interfaces and related components provide a standardized way of defining various UI elements (like buttons, displays, rows, etc.) and their behaviors.

### Element Interfaces

The new Element system consists of the following key interfaces:

- Element: The core interface for all UI elements. It defines properties such as background color, text color, and category. All UI elements will implement this interface.
- ElementCategory: Defines categories for UI elements, helping group them by types such as buttons, displays, rows and others.
- ElementCategoryStyle: Specifies the style of an element within a category (e.g., button styles, display styles).
- ElementColorStyle: Describes the color styles for elements, such as background and text colors.
- ElementLayout: Contains layout properties like alignment, text size, shape, etc., for positioning and styling elements.
- ElementData: Encapsulates both the Element and its ElementLayout, which includes layout properties. This makes it easy to manage both the element's behavior and its layout in one place.

### Key Benefits:

- Modularity: The new interfaces allow for a modular UI design where elements can be easily swapped or customized without breaking the underlying system.
- Flexibility: Custom styles and categories for elements can be defined, allowing for highly flexible UI components.
- Reusability: Elements are reusable across different parts of the project, improving code efficiency.

### How to Use

To work with the new Element system and create buttons, follow these steps:

1. Define a Button: Implement the Button interface with the required properties, such as symbol, category, and color styles.
2. Style the Button: Use the ButtonCategoryStyleBuilder to define custom styles for categories and symbols (e.g., arithmetic, control, number).
3. Assign Layout: Use the ElementLayout interface to define the button’s position and size within the layout.
4. Add to View: Create an instance of ButtonData to bind the button with its layout and style.
5. Display the Button: Render the button in the UI using your preferred UI framework (e.g., Jetpack Compose).

