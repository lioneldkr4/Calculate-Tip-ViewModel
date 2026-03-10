package com.example.propina

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.rememberWindowState
import ui.TipCalculatorScreen

/**
 * Punto de entrada de la app Desktop.
 * Compose for Desktop maneja el loop de eventos y el ciclo de
 * vida de la ventana.
 */
fun main() {
    println("[Main] 🚀 Iniciando aplicación Desktop - Tip Calculator")

    application {
        // Estado de la ventana (tamaño, posición)
        val windowState = rememberWindowState(
            width = 420.dp,
            height = 550.dp
        )

        Window(
            onCloseRequest = {
                // Este lambda se ejecuta al presionar la X de la ventana.
                // Compose destruirá el ViewModelStoreOwner de esta Window,
                // lo que a su vez llamará onCleared() del ViewModel.
                println("[Main] 🪟 Ventana cerrándose - " +
                        "el ViewModel será destruido tras onCloseRequest")
                exitApplication()
            },
            state = windowState,
            title = "Tip Calculator"
        ) {
            // MaterialTheme envuelve toda la UI para proveer colores,
            // tipografía y formas consistentes con Material Design 3.
            MaterialTheme {
                // TipCalculatorScreen está en commonMain (shared).
                // viewModel() dentro de la pantalla usará esta Window
                // como ViewModelStoreOwner.
                TipCalculatorScreen()
            }
        }
    }

    println("[Main] 👋 Aplicación Desktop finalizada")
}
