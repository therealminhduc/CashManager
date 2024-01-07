package com.epitech.cashmanagerinterface.features.payment

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.data.CartItem
import com.epitech.cashmanagerinterface.common.data.CreditCard
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreConstants
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreHelper
import com.epitech.cashmanagerinterface.common.navigation.components.TopAppBar
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.features.cart.components.TotalCartPrice
import com.epitech.cashmanagerinterface.ui.theme.darkOnPrimary
import com.epitech.cashmanagerinterface.ui.theme.lightGray
import com.epitech.cashmanagerinterface.ui.theme.lightBlue

@Composable
fun PaymentScreen(cartViewModel: CartViewModel = viewModel(), navController: NavController, scaffoldState: ScaffoldState, context: Context) {
    val cartItems: List<CartItem> = cartViewModel.getAllProductsInCart()

    var cardNumber by remember { mutableStateOf("") }
    var cardOwner by remember { mutableStateOf("") }
    var expirationDate by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    val scaffoldScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    val apiEndpoints = remember { ApiClient.createApiEndpoints() }
    val coroutineScope = rememberCoroutineScope()
    var userId by remember { mutableStateOf("null") }
    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)

    LaunchedEffect(Unit) {
        userId = preferenceDataStoreHelper.getPreference(PreferenceDataStoreConstants.USERID_KEY, "null")
    }

    Scaffold(
        topBar = { TopAppBar(screenName = NavItem.Payment.label) { navController.navigate(NavItem.Cart.route)} }
    ) {
        Text(
            text = "payment",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Button(
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
                value = cardNumber,
                onValueChange = {input -> cardNumber = input } )

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                label = { Text(style = MaterialTheme.typography.labelLarge, text = "Card owner" )},
                value = cardOwner,
                onValueChange = { input -> cardOwner = input } )

            Row {
                OutlinedTextField(
                    modifier = Modifier.width(260.dp),
                    label = { Text(style = MaterialTheme.typography.labelLarge, text = "Expiration MM/AA" )},
                    value = expirationDate,
                    onValueChange = { input -> expirationDate = input } )

                Spacer(modifier = Modifier.width(10.dp))

                OutlinedTextField(
                    modifier = Modifier.width(80.dp),
                    label = { Text(style = MaterialTheme.typography.labelLarge, text = "CVV" )},
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.NumberPassword,
                        imeAction = ImeAction.Done
                    ),
                    maxLines = 1,
                    value = cvv,
                    isError = cvv.length > 3,
                    onValueChange = { input ->
                        val trimmedInput = input.filter { it.isDigit() }
                        if (trimmedInput.length <= 3) {
                            cvv = trimmedInput
                        }
                    } )
            }

            Spacer(modifier = Modifier.width(15.dp))

            val cardInfo = CreditCard(cardNumber, cardOwner, cvv, expirationDate)
//            val cardInfo = CreditCard("7254582707472229", "Florian", "084", "11/2026")
            val cardInfoJsonString = Json.encodeToString(cardInfo)

            Button(
                modifier = Modifier
                    .width(350.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                onClick = {
                    Log.d("Payment screen", "userId: $userId")
                    if (cardNumber.isNotEmpty() && cardOwner.isNotEmpty() && cvv.isNotEmpty() && expirationDate.isNotEmpty()) {
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                val validBasket = apiEndpoints.validateBasket(userId, cardInfoJsonString)
                                scaffoldState.snackbarHostState.showSnackbar(validBasket, null, SnackbarDuration.Short)
                            } catch (e: Exception) {
                                scaffoldState.snackbarHostState.showSnackbar("There was a problem with the payment", null, SnackbarDuration.Short)
                            } finally {
                                isLoading = false
                            }
                        }
                    } else {
                        scaffoldScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Please fill in all fields", null, SnackbarDuration.Short)
                        }
                    }
                    softwareKeyboardController?.hide()
                }
            ) {
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(20.dp), color = lightWhite)
                } else {
                    Text(text = "Process my payment", style = MaterialTheme.typography.labelLarge, color = darkOnPrimary)
                }
            }
        ) {
            Text(text = "Back to Cart", style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}