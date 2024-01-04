package com.epitech.cashmanagerinterface.features.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.ui.theme.darkOnPrimary
import com.epitech.cashmanagerinterface.ui.theme.lightBlue

@Composable
fun GoToPaymentButton(navController: NavController) {
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
            colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
            onClick = { navController.navigate(NavItem.Payment.route) }
        ) {
            Text(text = "Go to payment", style = MaterialTheme.typography.labelLarge, color = darkOnPrimary)
        }
    }
}