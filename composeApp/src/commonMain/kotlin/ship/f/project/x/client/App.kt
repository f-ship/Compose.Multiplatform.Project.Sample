package ship.f.project.x.client

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import ship.f.engine.client.utils.serverdrivenui.RenderScreen
import ship.f.engine.client.utils.serverdrivenui.RenderingContext.CommonClient
import ship.f.engine.client.utils.serverdrivenui.components.ServerDrivenUITheme
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig

@Composable
fun App(
    client: CommonClient,
    config: ScreenConfig,
) {
    Column {
        Spacer(modifier = Modifier.height(32.dp))
        RenderScreen(
            screenConfig = mutableStateOf(config),
            client = client.apply {
                init(config)
            },
        )
    }
}

@Composable
@Preview
fun AppPreview() {
    ServerDrivenUITheme {
        App(
            client = CommonClient(),
            config = Initialization().config
        )
    }
}
