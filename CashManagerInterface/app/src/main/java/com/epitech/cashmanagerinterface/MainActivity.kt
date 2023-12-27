package com.epitech.cashmanagerinterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.epitech.cashmanagerinterface.common.MainScreenView
import com.epitech.cashmanagerinterface.common.statusbar.StatusBarColor
import com.epitech.cashmanagerinterface.ui.theme.navGray
import com.google.accompanist.permissions.ExperimentalPermissionsApi

/**
 * @ExperimentalPermissionsApi: is used to indicate that the code uses experimental api, used to access and manage runtime permissions.
 * @onCreate: is called when the activity is created. It sets up the app's UI and handles the camera permission request
 * CashManagerInterfaceTheme: defines the overall look of the app (colors, fonts and other visual elements)
 */
@ExperimentalPermissionsApi
class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StatusBarColor(color = navGray)
            MainScreenView()
        }
    }
}
