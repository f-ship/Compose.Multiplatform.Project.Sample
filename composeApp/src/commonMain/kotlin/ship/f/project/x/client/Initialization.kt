package ship.f.project.x.client

import kotlinx.serialization.encodeToString
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Component
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnFieldUpdate
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Widget
import ship.f.engine.shared.utils.serverdrivenui.action.Action.UpdateFieldState
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
                                validations = listOf(
                                    FieldState.Validation(
                                        regex = "\\A(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)\\Z",
                                        error = "Please enter a valid email address",
                                        isRequired = true,
                                    )
                                )
                            ),
                            triggerActions = listOf(
                                OnFieldUpdate(
                                    action = UpdateFieldState(),
                                )
                            )
                        ),
                        mSpace(),
                        Component(
                            id = id("TextField-Password"),
                            state = FieldState(
//                                placeholder = "●●●●●●●●",
                                placeholder = "••••••••",
                                label = "Password",
                                value = "",
                                fieldType = FieldState.FieldType.Password,
                                validations = listOf(
                                    FieldState.Validation(
                                        regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,}$",
                                        error = "Password must contain at least one digit, lower case letter, upper case letter, special character and no whitespace",
                                        isRequired = true,
                                    )
                                )
                            ),
                            triggerActions = listOf(
                                OnFieldUpdate(
                                    action = UpdateFieldState(),
                                )
                            )
                        ),
                    )
                ),
            )
        )
    )
    val encodedRemoteConfig = json.encodeToString(remoteConfig)
    val config = json.decodeFromString<ScreenConfig>(encodedRemoteConfig)
}