package ship.f.project.x.client

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ship.f.engine.client.utils.serverdrivenui.CommonClient
import ship.f.project.x.client.slices.initialization.Initialization

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "ProjectX",
    ) {
        val client = remember(Unit) { CommonClient().apply { navigate(Initialization().config) } }

        App(
            client = client,
        )
    }
}