package ship.f.project.x.shared

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform