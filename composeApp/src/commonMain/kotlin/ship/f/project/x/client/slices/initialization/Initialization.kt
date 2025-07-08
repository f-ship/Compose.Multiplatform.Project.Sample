package ship.f.project.x.client.slices.initialization

import kotlinx.serialization.encodeToString
import ship.f.engine.client.utils.serverdrivenui.temp.testConfig
import ship.f.engine.shared.utils.serverdrivenui.ElementConfig
import ship.f.engine.shared.utils.serverdrivenui.ScreenConfig
import ship.f.engine.shared.utils.serverdrivenui.action.Action
import ship.f.engine.shared.utils.serverdrivenui.action.Meta
import ship.f.engine.shared.utils.serverdrivenui.action.Target
import ship.f.engine.shared.utils.serverdrivenui.action.Trigger
import ship.f.engine.shared.utils.serverdrivenui.ext.id
import ship.f.engine.shared.utils.serverdrivenui.json.json
import ship.f.engine.shared.utils.serverdrivenui.state.*

val textList: Array<out ScreenConfig.Component<ComponentState>> = arrayOf(
    ScreenConfig.Component(
        state = TextState(
            value = "DisplayL",
            style = TextState.Style.DisplayLarge
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "DisplayM",
            style = TextState.Style.DisplayMedium
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "DisplayS",
            style = TextState.Style.DisplaySmall
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "HeadlineL",
            style = TextState.Style.HeadlineLarge
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "HeadlineM",
            style = TextState.Style.HeadlineMedium
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "HeadlineS",
            style = TextState.Style.HeadlineSmall
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "TitleL",
            style = TextState.Style.TitleLarge
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "TitleM",
            style = TextState.Style.TitleMedium
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "TitleS",
            style = TextState.Style.TitleSmall
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "BodyL",
            style = TextState.Style.BodyLarge
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "BodyM",
            style = TextState.Style.BodyMedium
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "BodyS",
            style = TextState.Style.BodySmall
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "LabelL",
            style = TextState.Style.LabelLarge
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "LabelM",
            style = TextState.Style.LabelMedium
        ),
    ),
    ScreenConfig.Component(
        state = TextState(
            value = "LabelS",
            style = TextState.Style.LabelSmall
        ),
    ),
)

val exampleChildren =
    listOf(
        ScreenConfig.Component(
            id = id("Local-Icon"),
            state = ImageState(
                src = ImageState.Source.Resource(
                    resource = "icon-back"
                ),
            )
        ),
        ScreenConfig.Widget(
            id = id("Empty-Column"),
            state = ColumnState(
                arrangement = Arrangement.Flex,
                children = listOf()
            )
        ),
        ScreenConfig.Component(
            id = id("Unknown-Local-Icon"),
            state = ImageState(
                src = ImageState.Source.Resource(
                    resource = "gpt"
                ),
            )
        ),
        ScreenConfig.Component(
            id = id("Remote-Image"),
            state = ImageState(
                src = ImageState.Source.Url(
                    url = "https://letsenhance.io/static/73136da51c245e80edc6ccfe44888a99/1015f/MainBefore.jpg"
                ),
            )
        ),
        mSpace(),
        ScreenConfig.Component(
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
            triggers = listOf(
                Trigger.OnFieldUpdateTrigger(
                    action = Action.UpdateState(),
                )
            )
        ),
        mSpace(),
        ScreenConfig.Component(
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
            triggers = listOf(
                Trigger.OnFieldUpdateTrigger(
                    action = Action.UpdateState(),
                )
            )
        ),
        mSpace(),
        *textList,
        mSpace(),
        ScreenConfig.Component(
            state = ToggleState(),
            triggers = listOf(
                Trigger.OnToggleUpdateTrigger(
                    action = Action.UpdateState(),
                )
            )
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Primary",
                buttonType = ButtonState.ButtonType.Primary,
            ),
            triggers = listOf(
                Trigger.OnStateUpdateTrigger(
                    action = Action.MatchValid(
                        targetIds = listOf(
                            Target(id("TextField-Email")),
                            Target(id("TextField-Password")),
                        )
                    )
                ),
                Trigger.OnClickTrigger(
                    action = Action.Navigate(),
                    metaID = id("Primary Button Meta"),
                )
            ),
            metas = mapOf(
                id("Primary Button Meta") to Meta.ScreenConfigMeta(
                    screenConfig = testConfig,
                ),
            ),
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Secondary Button 1",
                buttonType = ButtonState.ButtonType.Secondary,
            ),
            triggers = listOf(
                Trigger.OnClickTrigger(
                    action = Action.Navigate(),
                    metaID = id("Secondary Button 1"),
                ),
            ),
            metas = mapOf(
                id("Secondary Button 1") to Meta.ElementConfigMeta(
                    elementConfig = ElementConfig(
                        inside = id("Main-Screen"),
                        elements = listOf(
                            ScreenConfig.Component(
                                state = TextState(
                                    value = "Been added from a secondary button 1",
                                )
                            )
                        )
                    ),
                ),
            )
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Secondary Button 2",
                buttonType = ButtonState.ButtonType.Secondary,
            ),
            triggers = listOf(
                Trigger.OnClickTrigger(
                    action = Action.Navigate(),
                    metaID = id("Secondary Button 2"),
                ),
            ),
            metas = mapOf(
                id("Secondary Button 2") to Meta.ElementConfigMeta(
                    elementConfig = ElementConfig(
                        inside = id("Main-Screen"),
                        after = id("TextField-Password"),
                        elements = listOf(
                            ScreenConfig.Component(
                                state = TextState(
                                    value = "Been added from a secondary button 2",
                                )
                            )
                        )
                    ),
                ),
            )
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Secondary Button 3",
                buttonType = ButtonState.ButtonType.Secondary,
            ),
            triggers = listOf(
                Trigger.OnClickTrigger(
                    action = Action.Navigate(),
                    metaID = id("Secondary Button 3"),
                ),
            ),
            metas = mapOf(
                id("Secondary Button 3") to Meta.ElementConfigMeta(
                    elementConfig = ElementConfig(
                        inside = id("Empty-Column-Bottom"),
                        elements = listOf(
                            ScreenConfig.Component(
                                state = TextState(
                                    value = "Been added from a secondary button 3",
                                )
                            )
                        )
                    ),
                ),
            )
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Secondary Button 4",
                buttonType = ButtonState.ButtonType.Secondary,
            ),
            triggers = listOf(
                Trigger.OnClickTrigger(
                    action = Action.Navigate(),
                    metaID = id("Secondary Button 4"),
                ),
            ),
            metas = mapOf(
                id("Secondary Button 4") to Meta.ElementConfigMeta(
                    elementConfig = ElementConfig(
                        inside = id("Empty-Column-Bottom"),
                        after = id("Empty-Column-Bottom-Text 1"),
                        elements = listOf(
                            ScreenConfig.Component(
                                state = TextState(
                                    value = "Been added from a secondary button 4",
                                )
                            )
                        )
                    ),
                ),
            )
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Secondary Button 5",
                buttonType = ButtonState.ButtonType.Secondary,
            ),
            triggers = listOf(
                Trigger.OnClickTrigger(
                    action = Action.Navigate(),
                    metaID = id("Secondary Button 5"),
                ),
            ),
            metas = mapOf(
                id("Secondary Button 5") to Meta.ElementConfigMeta(
                    elementConfig = ElementConfig(
                        inside = id("Empty-Column-Bottom"),
                        after = id("Empty-Column-Bottom-Text 11"),
                        elements = listOf(
                            ScreenConfig.Component(
                                state = TextState(
                                    value = "Been added from a secondary button 5",
                                )
                            )
                        )
                    ),
                ),
            )
        ),
        mSpace(),
        ScreenConfig.Component(
            state = ButtonState(
                value = "Tertiary",
                buttonType = ButtonState.ButtonType.Tertiary,
            ),
        ),
        mSpace(),
        ScreenConfig.Widget(
            id = id("Empty-Column-Bottom"),
            state = ColumnState(
                children = listOf(
                    ScreenConfig.Component(
                        id = id("Empty-Column-Bottom-Text 1"),
                        state = TextState(
                            value = "Default Text 1"
                        )
                    ),
                    ScreenConfig.Component(
                        state = TextState(
                            value = "Default Text 2"
                        )
                    ),
                    ScreenConfig.Component(
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
        ScreenConfig.Widget(
            id = id("Main-Column"),
            state = ColumnState(
                arrangement = Arrangement.Flex,
                children = exampleChildren,
            ),
        )
    )
)
val encodedRemoteConfig = json.encodeToString(remoteConfig).apply {
    println(this)
}
val config = json.decodeFromString<ScreenConfig>(encodedRemoteConfig)