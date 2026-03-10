package com.example.propina

data class TipUiState(
    val billAmount: String = "",
    val tipPercentage: String = "15",
    val roundUp: Boolean = false,
    val tipResult: String = "$0.00"
)
