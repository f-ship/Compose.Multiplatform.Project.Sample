package ship.f.project.x.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ship.f.engine.client.utils.serverdrivenui.RenderScreen
import ship.f.engine.client.utils.serverdrivenui.CommonClient
import ship.f.engine.client.utils.serverdrivenui.components.ServerDrivenUITheme

@Composable
fun App(
    client: CommonClient,
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        val config = client.currentScreen!!
        RenderScreen(
            screenConfig = config,
            client = client
        )
    }
}

@Composable
@Preview
fun AppPreview() {
    ServerDrivenUITheme {
        App(
            client = CommonClient(),
        )
    }
}
