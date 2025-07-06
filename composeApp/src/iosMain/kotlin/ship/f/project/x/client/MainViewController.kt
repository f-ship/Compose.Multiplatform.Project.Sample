package ship.f.project.x.client

import androidx.compose.ui.window.ComposeUIViewController
import ship.f.engine.client.utils.serverdrivenui.theme.ServerDrivenUITheme

fun MainViewController() = ComposeUIViewController {
    ServerDrivenUITheme {
        App()
    }
}