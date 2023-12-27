package com.epitech.cashmanagerinterface.common.navigation.resources

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Profile: BottomNavItem("profile", Icons.Default.AccountCircle, "Profile")
    object Scanner: BottomNavItem("scanner", Icons.Default.Add, "Scanner")
    object Cart: BottomNavItem("cart", Icons.Default.List, "Cart")

}