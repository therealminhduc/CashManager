package com.epitech.cashmanagerinterface.features.user.profile

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.runtime.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.ui.theme.lightBlue
import com.epitech.cashmanagerinterface.ui.theme.lightCrimson
import com.epitech.cashmanagerinterface.ui.theme.lightWhite
import com.epitech.cashmanagerinterface.ui.theme.lightWhite2
import com.epitech.cashmanagerinterface.ui.theme.navGray
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreConstants
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreHelper
import androidx.compose.runtime.rememberCoroutineScope
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ProfileScreen(navController: NavController, context: Context) {

    val coroutineScope = rememberCoroutineScope()
    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)

    Scaffold(topBar = {
        TopAppBar(
            title = { Text("Profile") }, backgroundColor = navGray
        )
    }, content = {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ProfileInfoSection(context)

            Spacer(modifier = Modifier.weight(1f))

            Button(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically)
                .padding(horizontal = 10.dp, vertical = 50.dp),
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = lightCrimson),
                onClick = {
                    coroutineScope.launch {
                        preferenceDataStoreHelper.removePreference(PreferenceDataStoreConstants.USERID_KEY)
                    }
                    navController.navigate(NavItem.Login.route)
                }) {
                Text("Logout", style = MaterialTheme.typography.labelLarge, color = lightWhite2)
            }
        }
    })
}

@Composable
fun ProfileInfoSection(context: Context) {
    var userName by remember { mutableStateOf("") }
    var userId by remember { mutableStateOf("null") }

    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)

    val apiEndpoints = remember { ApiClient.createApiEndpoints() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(userId) {
        userId = preferenceDataStoreHelper.getPreference(PreferenceDataStoreConstants.USERID_KEY, "null")

        coroutineScope.launch {
            val user = apiEndpoints.getUserById(userId)
            userName = user?.username ?: ""
        }
    }

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
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Username: $userName",
                style = MaterialTheme.typography.labelLarge,
            )
        }
    }
}
