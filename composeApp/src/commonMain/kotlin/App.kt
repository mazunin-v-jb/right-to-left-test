import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

private var helloWorldArabic = "\u0645\u0631\u062D\u0628\u0627 \u0628\u0627\u0644\u0639\u0627\u0644\u0645"

@Composable
@Preview
fun App() {
    MaterialTheme {
        LazyColumn(Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("LTR Latin")
                        TextField("Hello world\nHello world")
                        Text("LTR Arabic")
                        TextField("$helloWorldArabic\n$helloWorldArabic")
                    }
                }
            }
            item {
                CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text("RTL Latin")
                        TextField("Hello world\nHello world")
                        Text("RTL Arabic")
                        TextField("$helloWorldArabic\n$helloWorldArabic")
                    }
                }
            }
        }
    }
}

@Composable
internal fun TextField(
    text: String = ""
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val state = rememberSaveable { mutableStateOf(text) }

    BasicTextField(
        modifier = Modifier.border(1.dp, Color.LightGray, RoundedCornerShape(8.dp)).padding(8.dp),
        value = state.value,
        singleLine = false,
        keyboardActions = KeyboardActions { keyboardController?.hide() },
        onValueChange = { state.value = it },
    )
}