package com.epitech.cashmanagerinterface.features.user.register

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.navigation.components.TopAppBar
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.ui.theme.lightBlue
import com.epitech.cashmanagerinterface.ui.theme.lightGray
import com.epitech.cashmanagerinterface.ui.theme.lightWhite
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2
import kotlinx.coroutines.launch

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun RegisterScreen(navController: NavController, scaffoldState: ScaffoldState) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val scaffoldScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    var isValidUsername by remember { mutableStateOf(false) }
    var isValidPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopAppBar(NavItem.Register.label) { navController.navigate(NavItem.Login.route) } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(lightWhite2)
                .wrapContentSize(Alignment.Center)
                .padding(16.dp)
        ) {
            Text(
                text = "Register",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                label = { Text("Username *") },
                placeholder = { Text("Enter your username") },
                value = username,
                onValueChange = { input ->
                    username = input
                    isValidUsername = input.isNotEmpty() },
            )

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                label = { Text("Password *") },
                placeholder = { Text("Enter your password") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                value = password,
                onValueChange = { input ->
                    password = input
                    isValidPassword = input.isNotEmpty() },
                visualTransformation = PasswordVisualTransformation()

            )

            OutlinedTextField(
                modifier = Modifier.width(350.dp),
                label = { Text("Confirm Password *") },
                placeholder = { Text("Confirm your password") },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                value = confirmPassword,
                onValueChange = { input -> confirmPassword = input },
                visualTransformation = PasswordVisualTransformation()
            )

            Spacer(modifier = Modifier.height(5.dp))
            Button(
                modifier = Modifier.width(350.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightBlue),
                onClick = {
                    if (isValidUsername && isValidPassword && password == confirmPassword) {
                        isLoading = true
                        /*TODO*/
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
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color.White
                    )
                }
            }
        }
    }
}