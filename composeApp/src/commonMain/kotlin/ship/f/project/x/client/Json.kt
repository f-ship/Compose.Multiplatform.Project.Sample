package ship.f.project.x.client

import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json

val json = Json {
    ignoreUnknownKeys = true
    prettyPrint = true
    encodeDefaults = true
    classDiscriminator = "type"
    classDiscriminatorMode = ClassDiscriminatorMode.ALL_JSON_OBJECTS
}