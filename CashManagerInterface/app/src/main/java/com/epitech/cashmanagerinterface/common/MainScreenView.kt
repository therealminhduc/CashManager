package com.epitech.cashmanagerinterface.common

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.epitech.cashmanagerinterface.common.navigation.NavigationGraph
import com.epitech.cashmanagerinterface.common.navigation.components.BottomNavigationBar

@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold (bottomBar = { BottomNavigationBar(navController = navController) }) {
        NavigationGraph(navController = navController)
    }
}