package com.epitech.cashmanagerinterface.features.user.login

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.data.User
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.ui.theme.darkBlue
import com.epitech.cashmanagerinterface.ui.theme.lightBlack
import com.epitech.cashmanagerinterface.ui.theme.lightBlue
import com.epitech.cashmanagerinterface.ui.theme.lightWhite
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun LoginScreen(navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var isValidUsername by remember { mutableStateOf(false) }
    var isValidPassword by remember { mutableStateOf(false) }

    val apiEndpoints = remember { ApiClient.createApiEndpoints() }
    val coroutineScope = rememberCoroutineScope()

    var offsetState = remember { mutableStateOf(0) }
    val focusRequester = remember { FocusRequester() }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightWhite2)
            .offset(y = offsetState.value.dp),
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
            modifier = Modifier
                .width(350.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { if (it.isFocused) offsetState.value =-80 else offsetState.value =0 },
            label = { Text(style = MaterialTheme.typography.labelLarge, text = "Username*") },
            placeholder = { Text(style = MaterialTheme.typography.labelLarge, text = "Enter your username") },
            value = username,
            onValueChange = { input ->
                username = input
                isValidUsername = input.isNotEmpty() },
        )

        OutlinedTextField(
            modifier = Modifier
                .width(350.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { if (it.isFocused) offsetState.value =-80 else offsetState.value =0 },
            label = { Text(style = MaterialTheme.typography.labelLarge, text = "Password*") },
            placeholder = { Text(style = MaterialTheme.typography.labelLarge, text = "Enter your password") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            value = password,
            isError = false,
            onValueChange = { input ->
                password = input
                isValidPassword = input.isNotEmpty() },
            visualTransformation = PasswordVisualTransformation()

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


            val user = User(username, password)
            val jsonString = Json.encodeToString(user)

            Button(
                modifier = Modifier
                    .height(35.dp)
                    .width(120.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                onClick = {
                    coroutineScope.launch {
                        // Sans ce bloc try catch, l'appli crash si les identifiants sont erronés
                        try {
                            apiEndpoints.login(jsonString)
                        } catch (e: Exception) {
                            // TODO: Afficher qu'il y a eu un problème de connexion
                        }
                    }
                }
            ) {
                Text(text = "Login", style = MaterialTheme.typography.labelLarge, color = lightWhite)
            }
        }
    }
}