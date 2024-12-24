package ship.f.project.x.client

import kotlinx.serialization.encodeToString
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Component
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Widget
import ship.f.engine.shared.utils.serverdrivenui.id
import ship.f.engine.shared.utils.serverdrivenui.state.Arrangement
import ship.f.engine.shared.utils.serverdrivenui.state.ColumnState
import ship.f.engine.shared.utils.serverdrivenui.state.FieldState
import ship.f.engine.shared.utils.serverdrivenui.state.mSpace

class Initialization {
    val remoteConfig = ScreenConfig(
        state = listOf(
            Widget(
                state = ColumnState(
                    arrangement = Arrangement.Flex,
                    children = listOf(
                        Component(
                            id = id("TextField-Email"),
                            state = FieldState(
                                placeholder = "Enter your email",
                                label = "Email",
                                value = "",
                            ),
                        ),
                        mSpace(),
                        Component(
                            id = id("TextField-Password"),
                            state = FieldState(
                                placeholder = "●●●●●●●●",
                                label = "Password",
                                value = "",
                            ),
                        ),
                    )
                ),
            )
        )
    )
    val encodedRemoteConfig = json.encodeToString(remoteConfig)
    val config = json.decodeFromString<ScreenConfig>(encodedRemoteConfig)
}