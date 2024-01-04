package com.epitech.cashmanagerinterface.common.navigation.resources

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavItem(val route: String, val icon: ImageVector, val label: String) {
    object Profile: NavItem("profile", Icons.Default.AccountCircle, "Profile")
    object Scanner: NavItem("scanner", Icons.Default.Add, "Scanner")
    object Cart: NavItem("cart", Icons.Default.List, "Cart")
    object Payment: NavItem("payment", Icons.Default.ExitToApp, label = "Payment")
    object Login: NavItem("login", Icons.Default.AccountCircle, label = "Login")
    object Register: NavItem("register", Icons.Default.AccountCircle, label = "Register")
}