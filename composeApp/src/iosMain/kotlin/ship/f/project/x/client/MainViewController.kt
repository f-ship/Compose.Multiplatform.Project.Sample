package ship.f.project.x.client

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import ship.f.engine.client.utils.serverdrivenui.RenderingContext.CommonClient
import ship.f.engine.client.utils.serverdrivenui.components.ServerDrivenUITheme

fun MainViewController() = ComposeUIViewController {
    val config = remember(Unit) { Initialization().config }
    val client = remember(Unit) { CommonClient().apply { pushScreen(config) } }
    ServerDrivenUITheme {
        Column {
            App(
                client = client,
                config = config,
            )
        }
    }
}