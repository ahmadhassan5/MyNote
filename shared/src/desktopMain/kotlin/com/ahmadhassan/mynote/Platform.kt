package com.ahmadhassan.mynote


class DesktopPlatform: Platform {
    override val name: String = "Desktop"
}

actual fun getPlatform(): Platform = DesktopPlatform()