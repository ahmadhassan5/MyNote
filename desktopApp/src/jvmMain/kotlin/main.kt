import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ahmadhassan.mynote.Application
import com.ahmadhassan.mynote.di.initKoin
import com.ahmadhassan.mynote.ui.NoteRootComponent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry

fun main() {
    initKoin()
    val root = runOnUiThread { NoteRootComponent(componentContext = DefaultComponentContext(
        LifecycleRegistry()
    )) }

    application {
        Window(title = "My Note", onCloseRequest = ::exitApplication) {
            Application(root)
        }
    }
}
