import com.example.calculatorApp.arguments.TestArguments
import com.example.calculatorApp.ui.theme.Black
import com.example.calculatorApp.utils.DisplayCalculatorList
import com.example.calculatorApp.utils.DisplayCalculatorMappings
import org.junit.jupiter.params.provider.Arguments

object TestArgumentsDisplay : TestArguments {

    private fun <T> provideDisplayColors(elements: Array<T>, colorMapping: (T) -> Any) =
        elements.map { element -> Arguments.of(element, colorMapping(element)) }.stream()

    fun provideInputsColors() = provideDisplayColors(
        elements = DisplayCalculatorList.inputs,
        colorMapping = { DisplayCalculatorMappings.inputsColorMap[it] ?: Black }
    )
}