package com.epitech.cashmanagerinterface.features.cart

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.features.cart.components.CartWithProduct
import com.epitech.cashmanagerinterface.features.cart.components.EmptyCart
import com.epitech.cashmanagerinterface.features.cart.components.GoToPaymentButton

@Composable
fun CartScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController) {
    val cartItems = cartViewModel.cartItems

    if (cartItems.isEmpty()) { EmptyCart() }

    if (cartItems.isNotEmpty()) {
        CartWithProduct(cartViewModel = cartViewModel)
        GoToPaymentButton(navController = navController)
    }
}