package ship.f.project.x.client.slices.initialization

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import ship.f.engine.client.core.Slice
import ship.f.engine.client.utils.serverdrivenui.RenderScreen
import ship.f.project.x.client.slices.initialization.InitializationSubPub.InitializationState

class InitializationSlice : Slice<InitializationState, InitializationSubPub>(
    subPubClass = InitializationSubPub::class,
) {
    @Composable
    override fun EntryPoint(state: MutableState<InitializationState>) {
        RenderScreen(
            screenConfig = state.value.client.currentScreen, // MutableState causes recomposition on every change to it
            client = state.value.client,
        )
    }

    @Composable
    override fun NotReadyEntryPoint(state: MutableState<InitializationState>): @Composable () -> Unit {
        return {
            Text("SplashNotReady")
        }
    }
}