import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Snackbar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
    fun SnackbarDemo() {
        Column {
            val snackbarVisibleState = remember { mutableStateOf(false) }

            Button(onClick = { snackbarVisibleState.value = !snackbarVisibleState.value }) {
                if (snackbarVisibleState.value) {
                    Text("Hide Snackbar")
                } else {
                    Text("Show Snackbar")
                }
            }
            if (snackbarVisibleState.value) {
                Snackbar(
                    text = { Text(text = "This is a snackbar!") },
                    action = {
                        Button(onClick = {}) {
                            Text("MyAction")
                        }
                    },
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }