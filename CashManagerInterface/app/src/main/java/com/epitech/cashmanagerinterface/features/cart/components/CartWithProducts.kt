package com.epitech.cashmanagerinterface.features.cart.components

import androidx.compose.foundation.background
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.common.data.ProductCodeRequest
import com.epitech.cashmanagerinterface.common.data.User
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.ui.theme.lightWhite
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2
import com.epitech.cashmanagerinterface.ui.theme.lightCrimson
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun CartWithProduct(cartViewModel: CartViewModel = viewModel(), userId: String) {
    val cartItems = cartViewModel.cartItems
    val apiEndpoints = remember { ApiClient.createApiEndpoints() }
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = Modifier
        .fillMaxSize()
        .padding(top = 10.dp, bottom = 130.dp)) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center)
        ) {
            items(cartItems) {cartItem ->
                ElevatedCard(
                    colors = CardDefaults.cardColors(
                        containerColor = lightWhite
                    ),
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
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
// Commenté car chaque doublon apparait indépendament dans la page panier pour le moment
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .clip(RoundedCornerShape(10.dp))
                            ) {
//                                IconButton(
//                                    onClick = { cartViewModel.decreaseQuantity(cartItem) }
//                                ) {
//                                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "Remove")
//                                }

                                Text(
                                    text = "Quantity: ${cartItem.quantity}",
                                    textAlign = TextAlign.End,
                                )

//                                IconButton(
//                                    onClick = { cartViewModel.increaseQuantity(cartItem) }
//                                ) {
//                                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "Add")
//                                }
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
                                    .background(lightWhite2),
                                model = cartItem.product.imgUrl,
                                contentDescription = "$cartItem.product.name preview"
                            )

                            IconButton(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(color = lightCrimson),
                                onClick = {
                                    val productCodeRequest = ProductCodeRequest(cartItem.product.code)
                                    val productCodeRequestToJsonString = Json.encodeToString(productCodeRequest)

                                    coroutineScope.launch {
                                        try {
                                            apiEndpoints.deleteAllProductsWithCode(userId, productCodeRequestToJsonString)
                                        } catch (e: Exception) {
                                            // TODO gérer l'erreur
                                        }
                                    }
                                    cartViewModel.removeCartItem(cartItem)
                                }
                            ) {
                                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = lightWhite2)
                            }
                        }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            val totalPrice = cartViewModel.calculateTotalPrice(cartItem)
                            Text(fontWeight = FontWeight.SemiBold, text = "${totalPrice}€")
                        }
                    }
                }
            }
        }
    }
}