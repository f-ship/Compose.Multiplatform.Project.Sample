package ship.f.project.x.client.slices.login

import ship.f.engine.client.core.State
import ship.f.engine.client.core.SubPub
import ship.f.project.x.client.slices.login.LoginSubPub.LoginState

class LoginSubPub : SubPub<LoginState>(
    requiredEvents = setOf(),
    nonRequiredEvents = setOf(),
) {
    data class LoginState(
        val noop: String = ""
    ) : State()

    override fun initState() = LoginState()

    override suspend fun onEvent() {

    }
}