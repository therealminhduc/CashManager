package com.epitech.cashmanagerinterface.features.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.features.cart.components.CartWithProduct
import com.epitech.cashmanagerinterface.features.cart.components.EmptyCart
import com.epitech.cashmanagerinterface.features.cart.components.GoToPaymentButton
import com.epitech.cashmanagerinterface.features.cart.components.TotalCartPrice

@Composable
fun CartScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController) {
    val cartItems = cartViewModel.cartItems

    if (cartItems.isEmpty()) { EmptyCart() }

    if (cartItems.isNotEmpty()) {
        CartWithProduct(cartViewModel = cartViewModel)

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            TotalCartPrice(cartViewModel = cartViewModel)
            GoToPaymentButton(navController = navController)
        }
    }
}