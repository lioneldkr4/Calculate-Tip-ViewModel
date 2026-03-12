package viewmodel

import androidx.lifecycle.ViewModel
import com.example.propina.TipUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.floor


class TipCalculatorViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TipUiState())

    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    // ── Inicialización ────────────────────────────────────────
    init {
        println("[TipCalculatorViewModel] ViewModel CREADO ")
        recalculateTip()
    }


    fun onBillAmountChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(billAmount = value)
        }
        recalculateTip()
    }

    fun onTipPercentageChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(tipPercentage = value)
        }
        recalculateTip()
    }

    fun onRoundUpChange(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(roundUp = enabled)
        }
        recalculateTip()
    }


    private fun recalculateTip() {
        val state = _uiState.value

        val bill = state.billAmount.toDoubleOrNull() ?: 0.0
        val percentage = state.tipPercentage.toDoubleOrNull() ?: 0.0

        var tip = bill * (percentage / 100.0)

        if (state.roundUp) {
            tip = floor(tip)
        }

        val formattedTip = formatCurrency(tip)

        _uiState.update { currentState ->
            currentState.copy(tipResult = formattedTip)
        }
    }


    private fun formatCurrency(amount: Double): String {
        // Redondear a 2 decimales para evitar artefactos de punto flotante
        val rounded = (amount * 100).toLong() / 100.0
        val intPart = rounded.toLong()
        val decPart = ((rounded - intPart) * 100).toLong()
        return "$${intPart}.${decPart.toString().padStart(2, '0')}"
    }


    override fun onCleared() {
        super.onCleared()
        println("[TipCalculatorViewModel]  onCleared() - " +
                "ViewModel DESTRUIDO ")
        println("[TipCalculatorViewModel] Los recursos han sido liberados.")
    }
}