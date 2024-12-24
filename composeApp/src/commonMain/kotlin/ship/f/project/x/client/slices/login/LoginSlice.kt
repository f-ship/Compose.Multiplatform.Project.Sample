package ship.f.project.x.client.slices.login

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import ship.f.engine.client.core.Slice

class LoginSlice : Slice<LoginSubPub.LoginState, LoginSubPub>(
    subPubClass = LoginSubPub::class,
) {
    @Composable
    override fun EntryPoint(state: MutableState<LoginSubPub.LoginState>) {
        Text("SplashReady")
    }

    @Composable
    override fun NotReadyEntryPoint(state: MutableState<LoginSubPub.LoginState>): @Composable () -> Unit {
        return {
            Text("SplashNotReady")
        }
    }
}