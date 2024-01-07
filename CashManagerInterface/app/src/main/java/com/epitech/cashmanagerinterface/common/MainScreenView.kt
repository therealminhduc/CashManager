package com.epitech.cashmanagerinterface.common

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.epitech.cashmanagerinterface.common.navigation.NavigationGraph
import com.epitech.cashmanagerinterface.common.navigation.components.BottomNavigationBar
import com.epitech.cashmanagerinterface.common.navigation.components.TopAppBar
import com.epitech.cashmanagerinterface.common.navigation.resources.NavItem
import com.epitech.cashmanagerinterface.common.statusbar.StatusBarColor
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.ui.theme.lightGray
import com.epitech.cashmanagerinterface.ui.theme.lightWhite

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val scaffoldState = rememberScaffoldState()

    val currentRoute by navController.currentBackStackEntryAsState()
    val isLoginOrRegister = currentRoute?.destination?.route in setOf(NavItem.Login.route, NavItem.Register.route)

    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = {
            if (!isLoginOrRegister) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) {
        StatusBarColor(color = lightWhite)
        NavigationGraph(navController = navController, cartViewModel = cartViewModel, scaffoldState = scaffoldState)
    }
}