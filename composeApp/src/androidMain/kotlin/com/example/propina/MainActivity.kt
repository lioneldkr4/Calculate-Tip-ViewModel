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


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        println("[MainActivity] onCreate() - la Activity fue creada/recreada")

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

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