package ship.f.project.x.client

import kotlinx.serialization.encodeToString
import ship.f.engine.client.core.Config
import ship.f.engine.client.core.Engine.init
import ship.f.engine.client.core.SubPubConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Component
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.ID
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnFieldUpdate
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnStateUpdate
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Widget
import ship.f.engine.shared.utils.serverdrivenui.action.Action
import ship.f.engine.shared.utils.serverdrivenui.action.Action.UpdateFieldState
import ship.f.engine.shared.utils.serverdrivenui.action.Target2
import ship.f.engine.shared.utils.serverdrivenui.state.Arrangement
import ship.f.engine.shared.utils.serverdrivenui.state.BottomSheetState
import ship.f.engine.shared.utils.serverdrivenui.state.CardState
import ship.f.engine.shared.utils.serverdrivenui.state.ColumnState
import ship.f.engine.shared.utils.serverdrivenui.state.FieldState
import ship.f.engine.shared.utils.serverdrivenui.state.FlexColumnState
import ship.f.engine.shared.utils.serverdrivenui.state.FlexGridState
import ship.f.engine.shared.utils.serverdrivenui.state.FlexRowState
import ship.f.engine.shared.utils.serverdrivenui.state.GridState
import ship.f.engine.shared.utils.serverdrivenui.state.RowState
import ship.f.engine.shared.utils.serverdrivenui.state.TextState
import ship.f.engine.shared.utils.serverdrivenui.state.UnknownComponentState
import ship.f.project.x.client.slices.login.LoginSubPub

object TestInitialization {
    private val config = Config(
        subPubConfig = mapOf(
            LoginSubPub::class to SubPubConfig(
                build = { LoginSubPub() },
                subPubs = mapOf()
            )
        )
    )

    private val screenConfig = ScreenConfig(
        state = listOf(
            Widget(
                state = ColumnState(
                    children = listOf(
                        Widget(
                            state = CardState(
                                children = listOf(
                                    Component(
                                        state = UnknownComponentState(
                                            value = ""
                                        ),
                                    ),
                                    Component(
                                        state = TextState(
                                            value = "",
                                            type = "UnknownStateText",
                                        ),
                                    ),
                                    Component(
                                        id = ID(id = "TextId", scope = ""),
                                        state = TextState(
                                            value = "",
                                            type = "TextState",
                                        ),
                                        triggerActions = listOf(
                                            OnStateUpdate(
                                                action = Action.UpdateValue(
                                                    targetIds = listOf(
                                                        Target2(
                                                            id = ID(id = "TextFieldId", scope = ""),
                                                        )
                                                    )
                                                )
                                            ),
                                        )
                                    ),
                                    Component(
                                        id = ID(id = "TextFieldId", scope = ""),
                                        state = FieldState(
                                            value = "",
                                            initialValue = "",
                                            placeholder = "",
                                            label = "",
                                        ),
                                        triggerActions = listOf(
                                            OnFieldUpdate(
                                                action = UpdateFieldState(),
                                            )
                                        )
                                    ),
                                )
                            ),
                        ),
                    )
                ),
            ),
            Widget(
                state = FlexColumnState(),
            ),
            Widget(
                state = RowState(
                    children = listOf(
                        Component(
                            id = ID(id = "TextFieldId2", scope = ""),
                            state = FieldState(
                                value = "",
                                initialValue = "",
                                placeholder = "",
                                label = "",
                            ),
                        )
                    ),
                    arrangement = Arrangement.Flex,
                ),
            ),
            Widget(
                state = FlexRowState(),
            ),
            Widget(
                state = CardState(),
            ),
            Widget(
                state = BottomSheetState(),
            ),
            Widget(
                state = GridState(),
            ),
            Widget(
                state = FlexGridState(),
            ),
            Widget(
                state = FlexGridState(
                    type = "UnknownStateGrid"
                ),
            )
        )
    )

    val screenConfigEncoded = json.encodeToString(screenConfig)
    val sConfigParsed = json.decodeFromString<ScreenConfig>(screenConfigEncoded)

    init {
        init(config)
    }
}
