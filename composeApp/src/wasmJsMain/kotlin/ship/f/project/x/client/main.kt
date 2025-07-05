package ship.f.project.x.client

import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import kotlinx.browser.document
import ship.f.engine.client.utils.serverdrivenui.CommonClient

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    ComposeViewport(document.body!!) {
        val client = remember(Unit) { CommonClient().apply { navigate(Initialization().config) } }

        App(
            client = client,
        )
    }
}