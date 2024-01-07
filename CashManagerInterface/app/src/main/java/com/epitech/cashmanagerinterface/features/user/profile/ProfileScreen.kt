package com.epitech.cashmanagerinterface.features.user.profile

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreConstants
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreHelper
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.ui.theme.darkOnPrimary
import com.epitech.cashmanagerinterface.ui.theme.lightCrimson
import com.epitech.cashmanagerinterface.ui.theme.navGray
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavController, context: Context) {
    val coroutineScope = rememberCoroutineScope()
    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(navGray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "My Profile Screen",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 20.sp
        )

        Button(
            modifier = Modifier
                .width(200.dp)
                .padding(bottom = 60.dp, end = 10.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = lightCrimson),
            onClick = {
                coroutineScope.launch {
                    preferenceDataStoreHelper.removePreference(PreferenceDataStoreConstants.USERID_KEY)
                }
                navController.navigate(NavItem.Login.route)
            }
        ) {
            Text(
                text = "Logout",
                style = MaterialTheme.typography.labelLarge,
                color = darkOnPrimary
            )
        }
    }
}


