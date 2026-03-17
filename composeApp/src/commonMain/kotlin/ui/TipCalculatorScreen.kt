package ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import viewmodel.TipCalculatorViewModel


@Composable
fun TipCalculatorScreen(
    viewModel: TipCalculatorViewModel = viewModel { TipCalculatorViewModel() }
) {

    val uiState by viewModel.uiState.collectAsState()

    val focusManager = LocalFocusManager.current

    DisposableEffect(Unit) {
        println("[TipCalculatorScreen] Composable ENTRANDO en composición")
        onDispose {
            println("[TipCalculatorScreen] Composable SALIENDO de composición")
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ── Título ────────────────────────────────────────
            Text(
                text = "Calculate Tip",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, bottom = 8.dp),
                textAlign = TextAlign.Center
            )

            // ── Campo: Monto de la cuenta ─────────────────────
            OutlinedTextField(
                value = uiState.billAmount,
                onValueChange = { viewModel.onBillAmountChange(it) },
                label = { Text("Bill Amount") },
                leadingIcon = {
                    Text(
                        text = "$",
                        modifier = Modifier.padding(start = 4.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next         // Avanzar al siguiente campo
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // ── Campo: Porcentaje de propina ──────────────────
            OutlinedTextField(
                value = uiState.tipPercentage,
                onValueChange = { viewModel.onTipPercentageChange(it) },
                label = { Text("Tip Percentage (%)") },
                trailingIcon = {
                    Text(
                        text = "%",
                        modifier = Modifier.padding(end = 8.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Done           // Ocultar teclado al terminar
                ),
                keyboardActions = KeyboardActions(
                    onDone = { focusManager.clearFocus() }
                ),
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )

            // ── Fila: Switch "Round up tip?" ──────────────────
            RoundUpRow(
                roundUp = uiState.roundUp,
                onRoundUpChange = { viewModel.onRoundUpChange(it) }
            )

            Divider(modifier = Modifier.padding(vertical = 4.dp))

            // ── Resultado de la propina ───────────────────────
            TipResultRow(tipResult = uiState.tipResult)

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


@Composable
private fun RoundUpRow(
    roundUp: Boolean,
    onRoundUpChange: (Boolean) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Round up tip?",
            style = MaterialTheme.typography.bodyLarge
        )
        Switch(
            checked = roundUp,
            onCheckedChange = onRoundUpChange
        )
    }
}


@Composable
private fun TipResultRow(tipResult: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Tip Amount",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = tipResult,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )
    }
}