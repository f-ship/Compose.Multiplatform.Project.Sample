package ship.f.project.x.client

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import ship.f.project.x.client.slices.login.LoginSlice

@Composable
fun App() {
    Initialization
    Column {
        LoginSlice().show()
    }
}