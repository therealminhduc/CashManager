package com.epitech.cashmanagerinterface.common.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epitech.cashmanagerinterface.common.navigation.resources.BottomNavItem
import com.epitech.cashmanagerinterface.features.cart.CartScreen
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.features.productScanner.CameraPermissionScreen
import com.epitech.cashmanagerinterface.features.userProfile.ProfileScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationGraph(navController: NavHostController, cartViewModel: CartViewModel) {
    NavHost(navController , startDestination = BottomNavItem.Scanner.route) {

        composable(BottomNavItem.Scanner.route) {
            CameraPermissionScreen(cartViewModel = cartViewModel)
        }

        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }

        composable(BottomNavItem.Cart.route) {
            CartScreen(cartViewModel = cartViewModel)
        }
    }
}