package ship.f.project.x.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import ship.f.engine.client.utils.serverdrivenui.RenderingContext.CommonClient
import ship.f.engine.client.utils.serverdrivenui.components.ServerDrivenUITheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val config = remember(Unit) { Initialization().config }
            val client = remember(Unit) { CommonClient().apply { init(config) } }
            ServerDrivenUITheme {
                Column {
                    App(
                        client = client,
                        config = config,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    ServerDrivenUITheme {
        App(
            client = CommonClient().apply { init(Initialization().config) },
            config = Initialization().config
        )
    }
}
