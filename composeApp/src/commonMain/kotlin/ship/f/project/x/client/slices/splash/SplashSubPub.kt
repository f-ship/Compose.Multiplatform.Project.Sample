//package ship.f.project.x.client.slices.splash
//
//import ship.f.engine.client.core.State
//import ship.f.engine.client.core.SubPub
//import ship.f.project.x.client.slices.splash.SplashSubPub.SplashState
//
//class SplashSubPub : SubPub<SplashState>(
//    requiredEvents = setOf(),
//    nonRequiredEvents = setOf(),
//) {
//    data class SplashState(
//        val noop: String = ""
//    ) : State()
//
//    override fun initState() = SplashState()
//
//    override suspend fun onEvent() {
//
//    }
//}