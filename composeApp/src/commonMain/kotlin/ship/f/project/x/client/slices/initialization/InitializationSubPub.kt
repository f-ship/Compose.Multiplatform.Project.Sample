package ship.f.project.x.client.slices.initialization

import ship.f.engine.client.core.State
import ship.f.engine.client.core.SubPub
import ship.f.engine.client.utils.serverdrivenui.CommonClient
import ship.f.project.x.client.slices.initialization.InitializationSubPub.InitializationState

class InitializationSubPub : SubPub<InitializationState>(
    requiredEvents = setOf(),
    nonRequiredEvents = setOf(),
) {
    data class InitializationState(
        val client: CommonClient = CommonClient.getClient().apply { navigate(config) },
    ) : State()

    override fun initState() = InitializationState()

    override suspend fun onEvent() {

    }
}