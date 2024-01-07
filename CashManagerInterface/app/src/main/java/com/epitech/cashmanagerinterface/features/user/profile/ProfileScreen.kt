package com.epitech.cashmanagerinterface.features.user.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.Composable
import com.epitech.cashmanagerinterface.ui.theme.lightBlue
import com.epitech.cashmanagerinterface.ui.theme.lightCrimson
import com.epitech.cashmanagerinterface.ui.theme.navGray


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Profile") },
                backgroundColor = navGray
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileInfoSection()

                Spacer(modifier = Modifier.weight(1f))

                LogoutButton()
            }
        }
    )
}

@Composable
fun ProfileInfoSection() {
    var userName by remember { mutableStateOf("User") }
    var password by remember { mutableStateOf("Password") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Username: $userName", fontSize = 24.sp)
        Text(text = "Password: $password", fontSize = 24.sp)

        Button(
            onClick = { /* Action to modify information */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = lightBlue),
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {
            Text("Edit")
        }
    }
}

@Composable
fun LogoutButton() {
    Button(
        onClick = { /* Action to logout */ },
        colors = ButtonDefaults.buttonColors(backgroundColor = lightCrimson),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
            .padding(horizontal = 16.dp, vertical = 50.dp)
    ) {
        Text("Logout")
    }
}

@Preview
@Composable
fun ProfilePreview() {
    ProfileScreen()
}
