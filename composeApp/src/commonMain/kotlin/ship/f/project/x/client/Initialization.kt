package ship.f.project.x.client

import kotlinx.serialization.encodeToString
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Component
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnFieldUpdateTrigger
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.TriggerAction.OnToggleUpdateTrigger
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Widget
import ship.f.engine.shared.utils.serverdrivenui.action.Action.UpdateFieldState
import ship.f.engine.shared.utils.serverdrivenui.action.Action.UpdateToggled
import ship.f.engine.shared.utils.serverdrivenui.id
import ship.f.engine.shared.utils.serverdrivenui.state.*
import ship.f.engine.shared.utils.serverdrivenui.state.ImageState.Source.Resource
import ship.f.engine.shared.utils.serverdrivenui.state.ImageState.Source.Url

class Initialization {
    val textList: Array<out Component> = arrayOf(
        Component(
            state = TextState(
                value = "DisplayL",
                style = TextState.Style.DisplayLarge
            ),
        ),
        Component(
            state = TextState(
                value = "DisplayM",
                style = TextState.Style.DisplayMedium
            ),
        ),
        Component(
            state = TextState(
                value = "DisplayS",
                style = TextState.Style.DisplaySmall
            ),
        ),
        Component(
            state = TextState(
                value = "HeadlineL",
                style = TextState.Style.HeadlineLarge
            ),
        ),
        Component(
            state = TextState(
                value = "HeadlineM",
                style = TextState.Style.HeadlineMedium
            ),
        ),
        Component(
            state = TextState(
                value = "HeadlineS",
                style = TextState.Style.HeadlineSmall
            ),
        ),
        Component(
            state = TextState(
                value = "TitleL",
                style = TextState.Style.TitleLarge
            ),
        ),
        Component(
            state = TextState(
                value = "TitleM",
                style = TextState.Style.TitleMedium
            ),
        ),
        Component(
            state = TextState(
                value = "TitleS",
                style = TextState.Style.TitleSmall
            ),
        ),
        Component(
            state = TextState(
                value = "BodyL",
                style = TextState.Style.BodyLarge
            ),
        ),
        Component(
            state = TextState(
                value = "BodyM",
                style = TextState.Style.BodyMedium
            ),
        ),
        Component(
            state = TextState(
                value = "BodyS",
                style = TextState.Style.BodySmall
            ),
        ),
        Component(
            state = TextState(
                value = "LabelL",
                style = TextState.Style.LabelLarge
            ),
        ),
        Component(
            state = TextState(
                value = "LabelM",
                style = TextState.Style.LabelMedium
            ),
        ),
        Component(
            state = TextState(
                value = "LabelS",
                style = TextState.Style.LabelSmall
            ),
        ),
    )
    val remoteConfig = ScreenConfig(
        lightColorScheme = ColorSchemeState(
            primary = 0xFFE64A19,
            onPrimary = 0xFFFFFFFF,
            onSecondaryContainer = 0xFF414651,
            background = 0xFFF9F9F9,
            onBackground = 0xFF1C1C1E,
            surface = 0xFFFFFF,
            onSurface = 0xFF1C1C1E,
            surfaceVariant = 0xFFF1F1F1,
            onSurfaceVariant = 0xFF6E6E6E,
            outline = 0xFFBDBDBD,
        ),
        darkColorScheme = ColorSchemeState(
            primary = 0xFFE64A19,
            onPrimary = 0xFFFFFFFF,
            onSecondaryContainer = 0xFFCECFD2,
            background = 0xFF1E1E1E,
            onBackground = 0xFFF1F1F1,
            surface = 0xFF121212,
            onSurface = 0xFFF1F1F1,
            surfaceVariant = 0xFF2A2A2A,
            onSurfaceVariant = 0xFFB0B0B0,
            outline = 0xFF444444,
        ),
        state = listOf(
            Widget(
                state = ColumnState(
                    arrangement = Arrangement.Flex,
                    children = listOf(
                        Component(
                            id = id("Local-Icon"),
                            state = ImageState(
                                src = Resource(
                                    resource = "icon-back"
                                ),
                            )
                        ),
                        Component(
                            id = id("Unknown-Local-Icon"),
                            state = ImageState(
                                src = Resource(
                                    resource = "gpt"
                                ),
                            )
                        ),
                        Component(
                            id = id("Remote-Image"),
                            state = ImageState(
                                src = Url(
                                    url = "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg"
                                ),
                            )
                        ),
                        mSpace(),
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
                                OnFieldUpdateTrigger(
                                    action = UpdateFieldState(),
                                )
                            )
                        ),
                        mSpace(),
                        Component(
                            id = id("TextField-Password"),
                            state = FieldState(
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
                                OnFieldUpdateTrigger(
                                    action = UpdateFieldState(),
                                )
                            )
                        ),
                        mSpace(),
                        *textList,
                        mSpace(),
                        Component(
                            state = ToggleState(),
                            triggerActions = listOf(
                                OnToggleUpdateTrigger(
                                    action = UpdateToggled(),
                                )
                            )
                        ),
                        mSpace(),
                        Component(
                            state = ButtonState(
                                value = "Primary",
                                buttonType = ButtonState.ButtonType.Primary,
                            ),
                        ),
                        mSpace(),
                        Component(
                            state = ButtonState(
                                value = "Secondary",
                                buttonType = ButtonState.ButtonType.Secondary,
                            ),
                        ),
                        mSpace(),
                        Component(
                            state = ButtonState(
                                value = "Tertiary",
                                buttonType = ButtonState.ButtonType.Tertiary,
                            ),
                        ),
                        mSpace(),
                    )
                ),
            )
        )
    )
    val encodedRemoteConfig = json.encodeToString(remoteConfig)
    val config = json.decodeFromString<ScreenConfig>(encodedRemoteConfig)
}