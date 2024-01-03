package com.epitech.cashmanagerinterface.features.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.epitech.cashmanagerinterface.common.navigation.resources.BottomNavItem
import com.epitech.cashmanagerinterface.ui.theme.navGray

@Composable
@Preview
fun CartScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController) {
    val cartItems = cartViewModel.cartItems

    if (cartItems.isEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(navGray)
                .wrapContentSize(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Text(
                    text = "Empty cart",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )

                Spacer(modifier = Modifier.width(5.dp))

                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
            }
        }
    }
    
    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 120.dp)) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
        ) {
            items(cartItems) {cartItem ->
                Text(text = "Your cart", fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(10.dp))
                OutlinedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surface
                    ),
                    border = BorderStroke(1.dp, Color.Black),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {

                    var quantity by remember { mutableIntStateOf(cartItem.quantity) }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = cartItem.product.name,
                                textAlign = TextAlign.Start,
                            )

                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                                IconButton(
                                    onClick = {
                                        if (quantity > 1) {
                                            quantity--
                                            cartViewModel.updateCartItem(cartItem, quantity)
                                        }
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Remove")
                                }

                                Text(
                                    text = "Quantity: ${cartItem.quantity}",
                                    textAlign = TextAlign.End,
                                )

                                IconButton(
                                    onClick = {
                                        quantity++
                                        cartViewModel.updateCartItem(cartItem, quantity)
                                    }
                                ) {
                                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Add")
                                }
                            }
                        }

                        AsyncImage(
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(top = 10.dp)
                                .align(Alignment.End),
                            model = cartItem.product.imgUrl,
                            contentDescription = "$cartItem.product.name preview"
                        )
                    }
                }
            }
        }
    }

    if (cartItems.isNotEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            Button(
                modifier = Modifier
                    .width(200.dp)
                    .padding(bottom = 60.dp, end = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0, 113, 227)),
                onClick = { navController.navigate(BottomNavItem.Payment.route) }
            ) {
                Text(text = "Go to payment", style = MaterialTheme.typography.labelLarge, color = Color.White)
            }
        }
    }
}