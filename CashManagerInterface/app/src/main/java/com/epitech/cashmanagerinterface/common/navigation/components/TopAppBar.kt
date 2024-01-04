package com.epitech.cashmanagerinterface.common.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import com.epitech.cashmanagerinterface.ui.theme.lightBlack
import com.epitech.cashmanagerinterface.ui.theme.lightWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(screenName: String, onBackClick: () -> Unit) {
    Column {
        androidx.compose.material3.TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = lightWhite,
                titleContentColor = lightBlack
            ),
            title = { Text(style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.SemiBold, text = screenName) },
            navigationIcon = {
                IconButton(onClick = { onBackClick() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
    }
}