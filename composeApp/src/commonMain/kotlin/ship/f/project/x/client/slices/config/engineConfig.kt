package ship.f.project.x.client.slices.config

import ship.f.engine.client.core.Config
import ship.f.engine.client.core.SubPubConfig
import ship.f.project.x.client.slices.initialization.InitializationSubPub

val engineConfig = Config(
    subPubConfig = mapOf(
        InitializationSubPub::class to SubPubConfig(
            build = { InitializationSubPub() },
            subPubs = mapOf()
        )
    )
)