package com.mayman.kmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform