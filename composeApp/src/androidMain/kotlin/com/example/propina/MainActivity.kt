package com.example.propina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import ui.TipCalculatorScreen

/**
 * Activity única de la aplicación Android.
 *
 * Delega todo el renderizado a Compose a través de setContent {}.
 * El ViewModel es creado/recuperado por viewModel() dentro de
 * TipCalculatorScreen, usando esta Activity como
 * ViewModelStoreOwner automáticamente.
 */
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Habilitar diseño edge-to-edge (sin barras de sistema visibles)
        enableEdgeToEdge()

        println("[MainActivity] onCreate() - la Activity fue creada/recreada")

        setContent {
            // MaterialTheme aplica el tema de Material Design 3
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // TipCalculatorScreen está definida en commonMain.
                    // Aquí se reutiliza la pantalla idéntica a Desktop.
                    // viewModel() usará esta Activity como owner.
                    TipCalculatorScreen()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        println("[MainActivity] onDestroy() - " +
                "isFinishing=$isFinishing | el ViewModel se limpiará si isFinishing=true")
    }
}