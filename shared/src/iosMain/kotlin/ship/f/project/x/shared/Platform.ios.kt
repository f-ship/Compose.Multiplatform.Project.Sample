package ship.f.project.x.shared

// import platform.UIKit.UIDevice

class IOSPlatform: Platform {
//    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override val name: String = "Windows machine placeholder"
}

actual fun getPlatform(): Platform = IOSPlatform()