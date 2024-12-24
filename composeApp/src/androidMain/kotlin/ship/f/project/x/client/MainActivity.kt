package ship.f.project.x.client

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ship.f.engine.Engine
import ship.f.engine.client.Client
import ship.f.engine.client.core.Core
import ship.f.engine.client.gen.Gen
import ship.f.engine.client.ksp.KSP
import ship.f.engine.client.utils.Utils
import ship.f.engine.client.utils.accessibility.Accessibility
import ship.f.engine.client.utils.lexeme.Lexeme
import ship.f.engine.client.utils.monitoring.Monitoring
import ship.f.engine.client.utils.monitoring.analytics.Analytics
import ship.f.engine.client.utils.monitoring.crash.Crash
import ship.f.engine.client.utils.monitoring.error.Error
import ship.f.engine.client.utils.monitoring.performance.Performance
import ship.f.engine.client.utils.networking.Networking
import ship.f.engine.client.utils.pushnotifications.PushNotifications
import ship.f.engine.client.utils.serverdrivenui.ServerDrivenUI
import ship.f.engine.kotlingen.dsl.DSL
import ship.f.engine.kotlingen.taskmanager.TaskManager
import ship.f.engine.kotlingen.writer.Writer
import ship.f.engine.sample.Sample
import ship.f.project.x.kotlingen.KotlinGen
import ship.f.engine.kotlingen.ksp.KSP as KotlinGenKSP
import ship.f.engine.kotlingen.ksp.writer.Writer as KSPWriter
import ship.f.engine.sample.client.Client as SampleClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Column {
                    App()

                    Text("${Engine::class.simpleName}")

                    Text("${Client::class.simpleName}")
                    Text("${Core::class.simpleName}")
                    Text("${KSP::class.simpleName}")
                    Text("${Utils::class.simpleName}")
                    Text("${Accessibility::class.simpleName}")
                    Text("${Lexeme::class.simpleName}")
                    Text("${Monitoring::class.simpleName}")
                    Text("${Analytics::class.simpleName}")
                    Text("${Crash::class.simpleName}")
                    Text("${Error::class.simpleName}")
                    Text("${Performance::class.simpleName}")
                    Text("${Networking::class.simpleName}")
                    Text("${PushNotifications::class.simpleName}")
                    Text("${ServerDrivenUI::class.simpleName}")

                    Text("${KotlinGen::class.simpleName}")
                    Text("${DSL::class.simpleName}")
                    Text("${Gen::class.simpleName}")
                    Text("${KotlinGenKSP::class.simpleName}")
                    Text("${KSPWriter::class.simpleName}")
                    Text("${TaskManager::class.simpleName}")
                    Text("${Writer::class.simpleName}")

                    Text("${Sample::class.simpleName}")
                    Text("${SampleClient::class.simpleName}")
                }
            }
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
//    App()
}