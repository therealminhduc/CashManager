package com.epitech.cashmanagerinterface.features.productScanner.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.CoroutineScope

@Composable
fun ProductInfoBottomSheet(
    productName: String,
    productPrice: String,
    productDescription: String,
    productImgUrl: String,
    isDialogVisible: Boolean,
    onClickAddButton: () -> Unit,
    onDismissRequest: () -> Unit,
    onConfirmationRequest: (Int) -> Unit,
    dialogTitle: String,
    scaffoldState: ScaffoldState,
    scaffoldScope: CoroutineScope
) {
    Row (modifier = Modifier
        .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier
                .padding(end = 16.dp)
        ) {
            Text(
                text = productName,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = productPrice,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.SemiBold
            )
            Text(text = productDescription)
        }

        Column(
            modifier = Modifier.padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White),
                model = productImgUrl,
                contentDescription = "$productName preview"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                modifier = Modifier
                    .width(70.dp)
                    .height(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(42, 170, 138)),
                onClick = {
                    onClickAddButton()
                }
            ) {
                Text(text = "Add", style = MaterialTheme.typography.labelSmall)
            }

            if (isDialogVisible) {
                QuantityAlertDialog(
                    onDismissRequest = { onDismissRequest() },
                    onConfirmation = {
                        quantity -> onConfirmationRequest(quantity)
                    },
                    dialogTitle = dialogTitle,
                    scaffoldState = scaffoldState,
                    scaffoldScope = scaffoldScope
                )
            }
        }
    }
}
