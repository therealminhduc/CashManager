package com.epitech.cashmanagerinterface.common.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.features.cart.CartScreen
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.features.payment.PaymentScreen
import com.epitech.cashmanagerinterface.features.productScanner.CameraPermissionScreen
import com.epitech.cashmanagerinterface.features.user.login.LoginScreen
import com.epitech.cashmanagerinterface.features.user.profile.ProfileScreen
import com.epitech.cashmanagerinterface.features.user.register.RegisterScreen
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NavigationGraph(navController: NavHostController, cartViewModel: CartViewModel, scaffoldState: ScaffoldState) {
    NavHost(navController , startDestination = NavItem.Login.route) {

        composable(NavItem.Scanner.route) {
            CameraPermissionScreen(cartViewModel = cartViewModel, scaffoldState = scaffoldState)
        }

        composable(NavItem.Profile.route) {
            ProfileScreen()
        }

        composable(NavItem.Cart.route) {
            CartScreen(cartViewModel = cartViewModel, navController = navController)
        }

        composable(NavItem.Payment.route) {
            PaymentScreen(navController = navController)
        }

        composable(NavItem.Login.route) {
            LoginScreen(navController = navController)
        }

        composable(NavItem.Register.route) {
            RegisterScreen(navController = navController)
        }
    }
}