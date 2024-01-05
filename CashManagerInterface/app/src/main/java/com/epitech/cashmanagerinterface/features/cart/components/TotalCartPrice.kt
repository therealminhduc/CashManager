package com.epitech.cashmanagerinterface.features.cart.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.epitech.cashmanagerinterface.features.cart.CartViewModel

@Composable
fun TotalCartPrice(cartViewModel: CartViewModel) {
    val totalCartPrice = cartViewModel.calculateTotalCartPrice()
    Text(
        text = "Total Price: $totalCartPrice",
        modifier = Modifier.padding(16.dp),
        fontWeight = FontWeight.Bold
    )
}