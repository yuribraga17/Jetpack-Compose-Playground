import androidx.compose.foundation.Text
import androidx.compose.material.Slider
import androidx.compose.runtime.*

@Composable
fun MySliderDemo() {
    var sliderPosition by remember { mutableStateOf(0f) }
    Text(text = sliderPosition.toString())
    Slider(value = sliderPosition, onValueChange = { sliderPosition = it })
}