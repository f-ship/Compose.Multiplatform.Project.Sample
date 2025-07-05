package ship.f.project.x.client

import kotlinx.serialization.encodeToString
import ship.f.engine.client.core.Config
import ship.f.engine.client.core.SubPubConfig
import ship.f.engine.client.utils.serverdrivenui.temp.testConfig
import ship.f.engine.shared.utils.serverdrivenui.ElementConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Component
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig.Widget
import ship.f.engine.shared.utils.serverdrivenui.action.Action.*
import ship.f.engine.shared.utils.serverdrivenui.action.Meta.ElementConfigMeta
import ship.f.engine.shared.utils.serverdrivenui.action.Meta.ScreenConfigMeta
import ship.f.engine.shared.utils.serverdrivenui.action.Target
import ship.f.engine.shared.utils.serverdrivenui.action.Trigger.*
import ship.f.engine.shared.utils.serverdrivenui.ext.id
import ship.f.engine.shared.utils.serverdrivenui.state.*
import ship.f.engine.shared.utils.serverdrivenui.state.Arrangement.Flex
import ship.f.engine.shared.utils.serverdrivenui.state.ButtonState.ButtonType.*
import ship.f.engine.shared.utils.serverdrivenui.state.FieldState.FieldType.Password
import ship.f.engine.shared.utils.serverdrivenui.state.FieldState.Validation
import ship.f.engine.shared.utils.serverdrivenui.state.ImageState.Source.Resource
import ship.f.engine.shared.utils.serverdrivenui.state.ImageState.Source.Url
import ship.f.engine.shared.utils.serverdrivenui.state.TextState.Style.*
import ship.f.project.x.client.slices.login.LoginSubPub

class Initialization {

    private val engineConfig = Config(
        subPubConfig = mapOf(
            LoginSubPub::class to SubPubConfig(
                build = { LoginSubPub() },
                subPubs = mapOf()
            )
        )
    )
    val textList: Array<out Component<ComponentState>> = arrayOf(
        Component(
            state = TextState(
                value = "DisplayL",
                style = DisplayLarge
            ),
        ),
        Component(
            state = TextState(
                value = "DisplayM",
                style = DisplayMedium
            ),
        ),
        Component(
            state = TextState(
                value = "DisplayS",
                style = DisplaySmall
            ),
        ),
        Component(
            state = TextState(
                value = "HeadlineL",
                style = HeadlineLarge
            ),
        ),
        Component(
            state = TextState(
                value = "HeadlineM",
                style = HeadlineMedium
            ),
        ),
        Component(
            state = TextState(
                value = "HeadlineS",
                style = HeadlineSmall
            ),
        ),
        Component(
            state = TextState(
                value = "TitleL",
                style = TitleLarge
            ),
        ),
        Component(
            state = TextState(
                value = "TitleM",
                style = TitleMedium
            ),
        ),
        Component(
            state = TextState(
                value = "TitleS",
                style = TitleSmall
            ),
        ),
        Component(
            state = TextState(
                value = "BodyL",
                style = BodyLarge
            ),
        ),
        Component(
            state = TextState(
                value = "BodyM",
                style = BodyMedium
            ),
        ),
        Component(
            state = TextState(
                value = "BodyS",
                style = BodySmall
            ),
        ),
        Component(
            state = TextState(
                value = "LabelL",
                style = LabelLarge
            ),
        ),
        Component(
            state = TextState(
                value = "LabelM",
                style = LabelMedium
            ),
        ),
        Component(
            state = TextState(
                value = "LabelS",
                style = LabelSmall
            ),
        ),
    )

    val exampleChildren =
        listOf(
            Component(
                id = id("Local-Icon"),
                state = ImageState(
                    src = Resource(
                        resource = "icon-back"
                    ),
                )
            ),
            Widget(
                id = id("Empty-Column"),
                state = ColumnState(
                    arrangement = Flex,
                    children = listOf()
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
                        Validation(
                            regex = "\\A(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?)\\Z",
                            error = "Please enter a valid email address",
                            isRequired = true,
                        )
                    )
                ),
                triggers = listOf(
                    OnFieldUpdateTrigger(
                        action = UpdateState(),
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
                    fieldType = Password,
                    validations = listOf(
                        Validation(
                            regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*+=])(?=\\S+$).{8,}$",
                            error = "Password must contain at least one digit, lower case letter, upper case letter, special character and no whitespace",
                            isRequired = true,
                        )
                    )
                ),
                triggers = listOf(
                    OnFieldUpdateTrigger(
                        action = UpdateState(),
                    )
                )
            ),
            mSpace(),
            *textList,
            mSpace(),
            Component(
                state = ToggleState(),
                triggers = listOf(
                    OnToggleUpdateTrigger(
                        action = UpdateState(),
                    )
                )
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Primary",
                    buttonType = Primary,
                ),
                triggers = listOf(
                    OnStateUpdateTrigger(
                        action = MatchValid(
                            targetIds = listOf(
                                Target(id("TextField-Email")),
                                Target(id("TextField-Password")),
                            )
                        )
                    ),
                    OnClickTrigger(
                        action = Navigate(
                            configMeta = ScreenConfigMeta(
                                screenConfig = testConfig,
                            ),
                        )
                    )
                ),
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Secondary Button 1",
                    buttonType = Secondary,
                ),
                triggers = listOf(
                    OnClickTrigger(
                        action = Navigate(
                            configMeta = ElementConfigMeta(
                                elementConfig = ElementConfig(
                                    inside = id("Main-Screen"),
                                    elements = listOf(
                                        Component(
                                            state = TextState(
                                                value = "Been added from a secondary button 1",
                                            )
                                        )
                                    )
                                ),
                            ),
                        )
                    ),
                )
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Secondary Button 2",
                    buttonType = Secondary,
                ),
                triggers = listOf(
                    OnClickTrigger(
                        action = Navigate(
                            configMeta = ElementConfigMeta(
                                elementConfig = ElementConfig(
                                    inside = id("Main-Screen"),
                                    after = id("TextField-Password"),
                                    elements = listOf(
                                        Component(
                                            state = TextState(
                                                value = "Been added from a secondary button 2",
                                            )
                                        )
                                    )
                                ),
                            ),
                        )
                    ),
                )
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Secondary Button 3",
                    buttonType = Secondary,
                ),
                triggers = listOf(
                    OnClickTrigger(
                        action = Navigate(
                            configMeta = ElementConfigMeta(
                                elementConfig = ElementConfig(
                                    inside = id("Empty-Column-Bottom"),
                                    elements = listOf(
                                        Component(
                                            state = TextState(
                                                value = "Been added from a secondary button 3",
                                            )
                                        )
                                    )
                                ),
                            ),
                        )
                    ),
                )
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Secondary Button 4",
                    buttonType = Secondary,
                ),
                triggers = listOf(
                    OnClickTrigger(
                        action = Navigate(
                            configMeta = ElementConfigMeta(
                                elementConfig = ElementConfig(
                                    inside = id("Empty-Column-Bottom"),
                                    after = id("Empty-Column-Bottom-Text 1"),
                                    elements = listOf(
                                        Component(
                                            state = TextState(
                                                value = "Been added from a secondary button 4",
                                            )
                                        )
                                    )
                                ),
                            ),
                        )
                    ),
                )
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Secondary Button 5",
                    buttonType = Secondary,
                ),
                triggers = listOf(
                    OnClickTrigger(
                        action = Navigate(
                            configMeta = ElementConfigMeta(
                                elementConfig = ElementConfig(
                                    inside = id("Empty-Column-Bottom"),
                                    after = id("Empty-Column-Bottom-Text 11"),
                                    elements = listOf(
                                        Component(
                                            state = TextState(
                                                value = "Been added from a secondary button 5",
                                            )
                                        )
                                    )
                                ),
                            ),
                        )
                    ),
                )
            ),
            mSpace(),
            Component(
                state = ButtonState(
                    value = "Tertiary",
                    buttonType = Tertiary,
                ),
            ),
            mSpace(),
            Widget(
                id = id("Empty-Column-Bottom"),
                state = ColumnState(
                    children = listOf(
                        Component(
                            id = id("Empty-Column-Bottom-Text 1"),
                            state = TextState(
                                value = "Default Text 1"
                            )
                        ),
                        Component(
                            state = TextState(
                                value = "Default Text 2"
                            )
                        ),
                        Component(
                            state = TextState(
                                value = "Default Text 3"
                            )
                        ),
                    )
                )
            ),
            mSpace(),
        )

    val remoteConfig = ScreenConfig(
        id = id("Main-Screen"),
        lightColorScheme = ColorSchemeState(
            primary = 0xFFE64A19,
            onPrimary = 0xFFFFFFFF,
            onSecondaryContainer = 0xFF414651,
            secondaryContainer = 0xFFFFFFFF,
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
            secondaryContainer = 0xFF1E1E1E,
            background = 0xFF1E1E1E,
            onBackground = 0xFFF1F1F1,
            surface = 0xFF121212,
            onSurface = 0xFFF1F1F1,
            surfaceVariant = 0xFF2A2A2A,
            onSurfaceVariant = 0xFFB0B0B0,
            outline = 0xFF444444,
        ),
        children = listOf(
            Widget(
                id = id("Main-Column"),
                state = ColumnState(
                    arrangement = Flex,
                    children = exampleChildren,
                ),
            )
        )
    )
    val encodedRemoteConfig = json.encodeToString(remoteConfig).apply {
        println(this)
    }
    val config = json.decodeFromString<ScreenConfig>(encodedRemoteConfig)
}