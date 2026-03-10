package com.example.propina

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform