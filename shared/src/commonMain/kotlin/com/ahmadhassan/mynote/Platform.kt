package com.ahmadhassan.mynote

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform