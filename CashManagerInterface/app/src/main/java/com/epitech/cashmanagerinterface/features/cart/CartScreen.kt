package com.epitech.cashmanagerinterface.features.cart

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
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
import coil.compose.AsyncImage
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.ui.theme.navGray

@Composable
@Preview
fun CartScreen(cartViewModel: CartViewModel = viewModel()) {
    val cartItems = cartViewModel.cartItems

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(navGray)
        .wrapContentSize(Alignment.Center)
    ) {
        items(cartItems) {cartItem ->
            OutlinedCard(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                ),
                border = BorderStroke(1.dp, Color.Black),
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = cartItem.product.name,
                            textAlign = TextAlign.Start,
                        )

                        Text(
                            text = "Quantity: ${cartItem.quantity}",
                            textAlign = TextAlign.End,
                        )
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