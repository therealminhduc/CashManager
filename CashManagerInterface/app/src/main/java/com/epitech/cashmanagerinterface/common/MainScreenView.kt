package com.epitech.cashmanagerinterface.common

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.epitech.cashmanagerinterface.common.navigation.NavigationGraph
import com.epitech.cashmanagerinterface.common.navigation.components.BottomNavigationBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold (bottomBar = { BottomNavigationBar(navController = navController) }) {
        NavigationGraph(navController = navController)
    }
}