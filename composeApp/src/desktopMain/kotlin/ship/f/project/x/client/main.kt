package ship.f.project.x.client

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ship.f.engine.client.utils.serverdrivenui.RenderingContext.CommonClient

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ProjectX",
    ) {
        val config = remember(Unit) { Initialization().config }
        val client = remember(Unit) { CommonClient().apply { init(config) } }

        App(
            client = client,
            config = config,
        )
    }
}