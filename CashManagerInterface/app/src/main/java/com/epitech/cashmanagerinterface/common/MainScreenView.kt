package com.epitech.cashmanagerinterface.common

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.epitech.cashmanagerinterface.common.navigation.NavigationGraph
import com.epitech.cashmanagerinterface.common.navigation.components.BottomNavigationBar
import com.epitech.cashmanagerinterface.common.statusbar.StatusBarColor
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.ui.theme.lightGray

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()
    val scaffoldState = rememberScaffoldState()

    Scaffold (
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        StatusBarColor(color = lightGray)
        NavigationGraph(navController = navController, cartViewModel = cartViewModel, scaffoldState = scaffoldState)
    }
}