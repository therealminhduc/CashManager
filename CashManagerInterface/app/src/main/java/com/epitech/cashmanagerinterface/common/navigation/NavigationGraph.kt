package com.epitech.cashmanagerinterface.common.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epitech.cashmanagerinterface.common.navigation.resources.BottomNavItem
import com.epitech.cashmanagerinterface.features.cart.CartScreen
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.features.payment.PaymentScreen
import com.epitech.cashmanagerinterface.features.productScanner.CameraPermissionScreen
import com.epitech.cashmanagerinterface.features.userProfile.ProfileScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationGraph(navController: NavHostController, cartViewModel: CartViewModel, scaffoldState: ScaffoldState) {
    NavHost(navController , startDestination = BottomNavItem.Scanner.route) {

        composable(BottomNavItem.Scanner.route) {
            CameraPermissionScreen(cartViewModel = cartViewModel, scaffoldState = scaffoldState)
        }

        composable(BottomNavItem.Profile.route) {
            ProfileScreen()
        }

        composable(NavItem.Cart.route) {
            CartScreen(cartViewModel = cartViewModel, navController = navController, context = navController.context)
        }

        composable(NavItem.Payment.route) {
            PaymentScreen(cartViewModel = cartViewModel, navController = navController, scaffoldState = scaffoldState, context = navController.context)
        }

        composable(NavItem.Login.route) {
            LoginScreen(navController = navController, context = navController.context, scaffoldState = scaffoldState)
        }

        composable(NavItem.Register.route) {
            RegisterScreen(navController = navController, scaffoldState = scaffoldState)
        }
    }
}