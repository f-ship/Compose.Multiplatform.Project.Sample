//package ship.f.project.x.client.slices.splash
//
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.MutableState
//import ship.f.engine.client.core.Slice
//
//class SplashSlice : Slice<SplashSubPub.SplashState, SplashSubPub>(
//    subPubClass = SplashSubPub::class,
//) {
//    @Composable
//    override fun EntryPoint(state: MutableState<SplashSubPub.SplashState>) {
//        Text("SplashReady")
//    }
//
//    @Composable
//    override fun NotReadyEntryPoint(state: MutableState<SplashSubPub.SplashState>): @Composable () -> Unit {
//        return {
//            Text("SplashNotReady")
//        }
//    }
//}