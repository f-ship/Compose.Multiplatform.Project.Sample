package ship.f.project.x.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import ship.f.engine.client.utils.serverdrivenui.CommonClient
import ship.f.engine.client.utils.serverdrivenui.theme.ServerDrivenUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            // TODO this is a test initialization, needs to be wrapped inside a subpub for a proper initialization
            val client = remember(Unit) { CommonClient.getClient().apply { pushScreen(Initialization().config) } }
            ServerDrivenUITheme {
                App(
                    client = client,
                )
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    ServerDrivenUITheme {
        App(
            client = CommonClient.getClient().apply { pushScreen(Initialization().config) },
        )
    }
}
