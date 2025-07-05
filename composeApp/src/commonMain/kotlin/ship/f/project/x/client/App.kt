package ship.f.project.x.client

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ship.f.engine.client.utils.serverdrivenui.CommonClient
import ship.f.engine.client.utils.serverdrivenui.RenderScreen
import ship.f.engine.client.utils.serverdrivenui.theme.ServerDrivenUITheme

@Composable
fun App(
    client: CommonClient,
) {
    RenderScreen(
        screenConfig = client.currentScreen, // MutableState causes recomposition on every change to it
        client = client
    )
}

@Composable
@Preview
fun AppPreview() {
    ServerDrivenUITheme {
        App(
            client = CommonClient.getClient(),
        )
    }
}
