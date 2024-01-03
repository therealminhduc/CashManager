package com.epitech.cashmanagerinterface.features.productScanner.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun QuantityAlertDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (Int) -> Unit,
    dialogTitle: String,
) {

    var productQuantity by remember {
        mutableStateOf("1")
    }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = dialogTitle, style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold) },
        confirmButton = {
            Button(
                modifier = Modifier
                    .height(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(42, 170, 138)),
                onClick = { onConfirmation(productQuantity.toInt()) }
            ) {
                Text(text = "Confirm", style = MaterialTheme.typography.labelSmall)
            }
        },
        dismissButton = {
            Button(
                modifier = Modifier
                    .height(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(220, 20, 60)),
                onClick = { onDismissRequest() }
            ) {
                Text(text = "Cancel", style = MaterialTheme.typography.labelSmall)
            }
        },
        text = {
            TextField(
                value = productQuantity,
                onValueChange = { productQuantity = it},
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onDismissRequest()
                    }
                ),
            )
        }
    )
}