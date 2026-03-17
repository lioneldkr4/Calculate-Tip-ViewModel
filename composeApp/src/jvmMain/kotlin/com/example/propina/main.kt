package com.example.propina

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import ui.TipCalculatorScreen


fun main() {
    println("[Main] Iniciando aplicación Desktop - Tip Calculator")

    application {
        val windowState = rememberWindowState(
            width = 420.dp,
            height = 550.dp
        )

        Window(
            onCloseRequest = {
                println("[Main] Ventana cerrándose - " +
                        "el ViewModel será destruido tras onCloseRequest")
                exitApplication()
            },
            state = windowState,
            title = "Tip Calculator"
        ) {
            MaterialTheme {

                TipCalculatorScreen()
            }
        }
    }

    println("[Main] Aplicación Desktop finalizada")
}
