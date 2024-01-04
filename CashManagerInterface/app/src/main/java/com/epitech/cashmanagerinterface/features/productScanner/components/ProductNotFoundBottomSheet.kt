package com.epitech.cashmanagerinterface.features.productScanner.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.epitech.cashmanagerinterface.ui.theme.lightCrimson

@Composable
fun ProductNotFoundBottomSheet(currentBarCodeValue: String) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Product $currentBarCodeValue is not available",
            style = MaterialTheme.typography.labelLarge,
            color = lightCrimson,
            fontWeight = FontWeight.Bold
        )
    }
}