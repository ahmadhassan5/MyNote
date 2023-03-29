import androidx.compose.ui.window.application
import com.ahmadhassan.mynote.Application
import com.ahmadhassan.mynote.di.initKoin
import moe.tlaster.precompose.PreComposeWindow

fun main() {
    initKoin()

    application {
        PreComposeWindow(title = "My Note", onCloseRequest = ::exitApplication) {
            Application()
        }
    }
}
