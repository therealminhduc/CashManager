package com.epitech.cashmanagerinterface.features.user.login

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.ui.theme.darkBlue
import com.epitech.cashmanagerinterface.ui.theme.lightBlack
import com.epitech.cashmanagerinterface.ui.theme.lightBlue
import com.epitech.cashmanagerinterface.ui.theme.lightWhite
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun LoginScreen(navController: NavController) {
    var isValidUsername by remember {
        mutableStateOf(false)
    }

    var isValidPassword by remember {
        mutableStateOf(false)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightWhite2),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.SemiBold,
            color = lightBlack,
            text = "Login"
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            modifier = Modifier.width(350.dp),
            label = { Text(style = MaterialTheme.typography.labelLarge, text = "Username *") },
            placeholder = { Text(style = MaterialTheme.typography.labelLarge, text = "Enter your username") },
            value = "",
            onValueChange = { input -> isValidUsername = input.isNotEmpty() },
        )

        OutlinedTextField(
            modifier = Modifier.width(350.dp),
            label = { Text(style = MaterialTheme.typography.labelLarge, text = "Password * ") },
            placeholder = { Text(style = MaterialTheme.typography.labelLarge, text = "Enter your password") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            value = "",
            isError = false,
            onValueChange = { input -> isValidPassword = input.isNotEmpty() },
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.width(350.dp)
        ) {
            TextButton(
                modifier = Modifier
                    .background(lightWhite2),
                shape = RoundedCornerShape(12.dp),
                onClick = { navController.navigate(NavItem.Register.route) }
            ) {
                Text(
                    style = MaterialTheme.typography.labelLarge,
                    textDecoration = TextDecoration.Underline,
                    color = darkBlue,
                    text = "Not an user yet? Register",
                )
            }

            Button(
                modifier = Modifier
                    .height(35.dp)
                    .width(120.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                onClick = { /*TODO*/ }
            ) {
                Text(text = "Login", style = MaterialTheme.typography.labelLarge, color = lightWhite)
            }
        }
    }
}