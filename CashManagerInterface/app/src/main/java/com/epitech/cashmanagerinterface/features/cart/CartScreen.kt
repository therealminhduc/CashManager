package com.epitech.cashmanagerinterface.features.cart

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreConstants
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreHelper
import com.epitech.cashmanagerinterface.features.cart.components.CartWithProduct
import com.epitech.cashmanagerinterface.features.cart.components.EmptyCart
import com.epitech.cashmanagerinterface.features.cart.components.GoToPaymentButton
import com.epitech.cashmanagerinterface.features.cart.components.TotalCartPrice
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2

@Composable
fun CartScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController, context: Context) {
    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)
    var userId by remember { mutableStateOf("null") }
    var cartItems by remember { mutableStateOf(mutableListOf<CartItem>()) }
    val apiEndpoints = remember { ApiClient.createApiEndpoints() }

    LaunchedEffect(Unit) {
        userId = preferenceDataStoreHelper.getPreference(PreferenceDataStoreConstants.USERID_KEY, "null")

        if (userId != "null") {
            val productList = apiEndpoints.getUsersBasket(userId)
            cartItems = mutableListOf<CartItem>()
            for (product in productList) {
                cartItems.add(CartItem(product, 1))
            }
            cartViewModel.setCart(cartItems)
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