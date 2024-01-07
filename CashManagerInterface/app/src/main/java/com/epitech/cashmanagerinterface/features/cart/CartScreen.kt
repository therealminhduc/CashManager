package com.epitech.cashmanagerinterface.features.cart

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreConstants
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreHelper
import com.epitech.cashmanagerinterface.features.cart.components.CartWithProduct
import com.epitech.cashmanagerinterface.features.cart.components.EmptyCart
import com.epitech.cashmanagerinterface.features.cart.components.GoToPaymentButton
import com.epitech.cashmanagerinterface.features.cart.components.TotalCartPrice
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.data.CartItem

@Composable
fun CartScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController, context: Context) {
    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)
    var userId by remember { mutableStateOf("null") }
    var cartItems by remember { mutableStateOf(mutableListOf<CartItem>()) }
    val apiEndpoints = remember { ApiClient.createApiEndpoints() }

    LaunchedEffect(Unit) {
        userId = preferenceDataStoreHelper.getPreference(PreferenceDataStoreConstants.USERID_KEY, "null")

        if (userId != "null") {
            try {
                val productList = apiEndpoints.getUsersBasket(userId)
                cartItems = mutableListOf<CartItem>()
                for (product in productList) {
                    cartItems.add(CartItem(product, 1))
                }
                cartViewModel.setCart(cartItems)
            } catch (e: Exception) {

            }
        }
    }

    if (cartItems.isEmpty()) { EmptyCart() }

    if (cartItems.isNotEmpty()) {
        CartWithProduct(cartViewModel = cartViewModel, userId = userId)

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