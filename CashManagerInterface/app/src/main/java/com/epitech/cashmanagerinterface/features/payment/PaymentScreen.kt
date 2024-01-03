package com.epitech.cashmanagerinterface.features.payment

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.ui.theme.navGray

@Composable
fun PaymentScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(navGray)
            .wrapContentSize(Alignment.Center)
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
                .width(200.dp)
                .padding(top = 16.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                // Navigate back to the CartScreen when the button is clicked
                navController.popBackStack()
            }
        ) {
            Text(text = "Back to Cart", style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}