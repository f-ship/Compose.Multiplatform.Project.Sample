package ship.f.project.x.client

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import ship.f.engine.client.utils.serverdrivenui.RenderingContext.CommonClient

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val config = remember(Unit) { Initialization().config }
        val client = remember(Unit) { CommonClient().apply { init(config) } }

        App(
            client = client,
            config = config,
        )
    }
}