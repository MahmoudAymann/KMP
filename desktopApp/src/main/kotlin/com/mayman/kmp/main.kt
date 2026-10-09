package com.mayman.kmp

import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mayman.kmp.di.initKoin
import io.ktor.client.engine.okhttp.OkHttp

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "KMP",
        ) {
            App()
        }
    }
}