package ship.f.project.x.client

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import ship.f.engine.client.utils.serverdrivenui.CommonClient
import ship.f.engine.client.utils.serverdrivenui.theme.ServerDrivenUITheme

fun MainViewController() = ComposeUIViewController {
    val client = remember(Unit) { CommonClient.getClient().apply { pushScreen(Initialization().config) } }
    ServerDrivenUITheme {
        App(
            client = client,
        )
    }
}