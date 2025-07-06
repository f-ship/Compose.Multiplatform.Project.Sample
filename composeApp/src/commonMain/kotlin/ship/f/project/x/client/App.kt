package ship.f.project.x.client

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import ship.f.engine.client.utils.serverdrivenui.theme.ServerDrivenUITheme
import ship.f.project.x.client.slices.initialization.InitializationSlice

@Composable
fun App() {
    ServerDrivenUITheme {
        InitializationSlice().Show()
    }
}

@Composable
@Preview
fun AppPreview() {
    App()
}
