package com.epitech.cashmanagerinterface.features.payment

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.common.navigation.components.TopAppBar
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.features.cart.components.TotalCartPrice
import com.epitech.cashmanagerinterface.ui.theme.darkOnPrimary
import com.epitech.cashmanagerinterface.ui.theme.lightBlue
import com.epitech.cashmanagerinterface.ui.theme.lightWhite
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun PaymentScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController) {
    
    val cartItems: List<CartItem> = cartViewModel.getAllProductsInCart()

    Scaffold(
        topBar = { TopAppBar(screenName = NavItem.Payment.label) { navController.navigate(NavItem.Cart.route)} }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightWhite2),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ElevatedCard(
                colors = CardDefaults.cardColors(
                    containerColor = lightWhite
                ),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column {
                    for (cartItem in cartItems) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(fontWeight = FontWeight.SemiBold, text = cartItem.product.name)
                            Text(text = "${cartItem.product.price} x${cartItem.quantity}")
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            TotalCartPrice(cartViewModel = cartViewModel)

            Divider(color = lightWhite, thickness = 3.dp, modifier = Modifier.width(370.dp))

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                label = { Text(style = MaterialTheme.typography.labelLarge, text = "Card number" )},
                value = "",
                onValueChange = {} )

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                label = { Text(style = MaterialTheme.typography.labelLarge, text = "Card owner" )},
                value = "",
                onValueChange = {} )

            Row {
                OutlinedTextField(
                    modifier = Modifier.width(260.dp),
                    label = { Text(style = MaterialTheme.typography.labelLarge, text = "Expiration MM/AA" )},
                    value = "",
                    onValueChange = {} )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    modifier = Modifier.width(80.dp),
                    label = { Text(style = MaterialTheme.typography.labelLarge, text = "CVV" )},
                    value = "",
                    onValueChange = {} )
            }

            Spacer(modifier = Modifier.width(15.dp))

            Button(
                modifier = Modifier
                    .width(350.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                onClick = { navController.navigate(NavItem.Payment.route) }
            ) {
                Text(text = "Process my payment", style = MaterialTheme.typography.labelLarge, color = darkOnPrimary)
            }
        }
    }
}