package viewmodel

import androidx.lifecycle.ViewModel
import com.example.propina.TipUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.math.ceil

/**
 * ViewModel de la calculadora de propinas.
 *
 * Al ser parte de commonMain, el mismo código se usa desde
 * Android y Desktop sin ninguna duplicación.
 */
class TipCalculatorViewModel : ViewModel() {

    // ── Estado reactivo de la UI ──────────────────────────────
    // MutableStateFlow es la fuente de verdad; solo este ViewModel
    // puede modificarla. La UI observa la versión inmutable (StateFlow).
    private val _uiState = MutableStateFlow(TipUiState())

    /**
     * Flujo de solo lectura expuesto a la UI.
     * La pantalla lo observa con collectAsState() para
     * recomponerse automáticamente ante cualquier cambio.
     */
    val uiState: StateFlow<TipUiState> = _uiState.asStateFlow()

    // ── Inicialización ────────────────────────────────────────
    init {
        println("[TipCalculatorViewModel] ViewModel CREADO - " +
                "instancia: ${hashCode()}")
        // Calcular estado inicial con valores por defecto
        recalculateTip()
    }

    // ── Eventos de la UI → métodos públicos ───────────────────

    /**
     * Actualiza el monto de la cuenta y recalcula la propina.
     * Se llama desde onValueChange del TextField de monto.
     */
    fun onBillAmountChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(billAmount = value)
        }
        recalculateTip()
    }

    /**
     * Actualiza el porcentaje de propina y recalcula.
     * Se llama desde onValueChange del TextField de porcentaje.
     */
    fun onTipPercentageChange(value: String) {
        _uiState.update { currentState ->
            currentState.copy(tipPercentage = value)
        }
        recalculateTip()
    }

    /**
     * Cambia el estado del switch "Redondear propina" y recalcula.
     */
    fun onRoundUpChange(enabled: Boolean) {
        _uiState.update { currentState ->
            currentState.copy(roundUp = enabled)
        }
        recalculateTip()
    }

    // ── Lógica de negocio privada ──────────────────────────────

    /**
     * Calcula la propina a partir del estado actual.
     *
     * Lógica extraída del codelab original "Calculate Tip":
     *  1. Parsear billAmount y tipPercentage a Double
     *  2. Calcular: tip = billAmount * (tipPercentage / 100)
     *  3. Si roundUp == true: tip = ceil(tip)
     *  4. Formatear el resultado como string con símbolo $
     *
     * Este metodo es privado: la UI nunca lo llama directamente,
     * sino que lo dispara indirectamente mediante los métodos on*.
     */
    private fun recalculateTip() {
        val state = _uiState.value

        // Convertir strings a Double; si no son válidos → 0.0
        val bill = state.billAmount.toDoubleOrNull() ?: 0.0
        val percentage = state.tipPercentage.toDoubleOrNull() ?: 0.0

        // Cálculo principal de la propina
        var tip = bill * (percentage / 100.0)

        // Redondeo opcional al entero superior
        if (state.roundUp) {
            tip = ceil(tip)
        }

        // Formatear como moneda con 2 decimales
        val formattedTip = formatCurrency(tip)

        // Actualizar solo el campo tipResult del estado
        _uiState.update { currentState ->
            currentState.copy(tipResult = formattedTip)
        }
    }

    /**
     * Formatea un Double como string de moneda "$X.XX".
     * Implementación expect/actual no necesaria aquí:
     * usamos formato manual compatible con todas las plataformas.
     */
    private fun formatCurrency(amount: Double): String {
        // Redondear a 2 decimales para evitar artefactos de punto flotante
        val rounded = (amount * 100).toLong() / 100.0
        val intPart = rounded.toLong()
        val decPart = ((rounded - intPart) * 100).toLong()
        return "$${intPart}.${decPart.toString().padStart(2, '0')}"
    }

    // ── Ciclo de vida del ViewModel ───────────────────────────

    /**
     * Llamado automáticamente cuando el ViewModelStoreOwner
     * (Activity en Android, Window en Desktop) es destruido.
     *
     * En Desktop: se invoca al cerrar la ventana.
     * En Android: se invoca al destruir la Activity o al hacer
     *             back definitivo (pop del back stack sin retorno).
     *
     * Propósito didáctico: demostrar que el ViewModel tiene un
     * ciclo de vida bien definido y se limpia correctamente.
     */
    override fun onCleared() {
        super.onCleared()
        println("[TipCalculatorViewModel]  onCleared() - " +
                "ViewModel DESTRUIDO - instancia: ${hashCode()}")
        println("[TipCalculatorViewModel] Los recursos han sido liberados.")
    }
}