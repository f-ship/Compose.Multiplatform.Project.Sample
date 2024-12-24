package ship.f.project.x.client

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import ship.f.engine.Engine
import ship.f.engine.client.core.Engine as Core

@Composable
fun App() {
    Column {
        Text("${Engine::class.simpleName}")
        Text("$Core")
    }
}