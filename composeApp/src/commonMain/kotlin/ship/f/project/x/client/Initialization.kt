package ship.f.project.x.client

import ship.f.engine.client.core.Config
import ship.f.engine.client.core.Engine
import ship.f.engine.client.core.SubPubConfig
import ship.f.project.x.client.slices.login.LoginSubPub

object Initialization {
    private val config = Config(
        subPubConfig = mapOf(
            LoginSubPub::class to SubPubConfig(
                build = { LoginSubPub() },
                subPubs = mapOf()
            )
        )
    )
    init {
        Engine.init(config)
    }
}