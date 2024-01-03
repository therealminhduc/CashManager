package com.epitech.cashmanagerinterface.features.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.ui.theme.navGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartWithProduct(cartViewModel: CartViewModel = viewModel()) {
    val cartItems = cartViewModel.cartItems
    val dismissState = rememberDismissState()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(bottom = 120.dp)) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
        ) {
            items(cartItems) {cartItem ->
                ElevatedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = navGray
                    ),
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

                            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(2.dp)) {
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

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color.White),
                                model = cartItem.product.imgUrl,
                                contentDescription = "$cartItem.product.name preview"
                            )


                            IconButton(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = Color(220, 20, 60)),
                                onClick = {
                                    cartViewModel.removeCartItem(cartItem)
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}