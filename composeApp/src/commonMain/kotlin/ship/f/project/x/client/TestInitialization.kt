package ship.f.project.x.client

import kotlinx.serialization.encodeToString
import ship.f.engine.client.core.Config
import ship.f.engine.client.core.Engine.init
import ship.f.engine.client.core.SubPubConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.*
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnFieldUpdateTrigger
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnStateUpdateTrigger
import ship.f.engine.shared.utils.serverdrivenui.action.Action
import ship.f.engine.shared.utils.serverdrivenui.action.Action.UpdateFieldState
import ship.f.engine.shared.utils.serverdrivenui.action.Target2
import ship.f.engine.shared.utils.serverdrivenui.state.*
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
                                            OnStateUpdateTrigger(
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
                                            OnFieldUpdateTrigger(
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
