import androidx.compose.ui.window.application
import com.ahmadhassan.mynote.di.initKoin
import com.ahmadhassan.mynote.ui.NoteRoot
import moe.tlaster.precompose.PreComposeWindow

fun main() {
    initKoin()

    application {
        PreComposeWindow(title = "My Note", onCloseRequest = ::exitApplication) {
            NoteRoot()
        }
    }
}