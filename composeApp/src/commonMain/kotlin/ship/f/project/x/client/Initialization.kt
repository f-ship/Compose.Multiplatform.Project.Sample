package ship.f.project.x.client

import ship.f.engine.client.core.Config
import ship.f.engine.client.core.Engine

object Initialization {
    private val config = Config(

    )
    init {
        Engine.init(config)
    }
}