package com.epitech.cashmanagerinterface

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import com.epitech.cashmanagerinterface.features.productScanner.ProductScannerCameraPreview
import com.epitech.cashmanagerinterface.ui.theme.CashManagerInterfaceTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState

/**
 * @ExperimentalPermissionsApi: is used to indicate that the code uses experimental api, used to access and manage runtime permissions.
 * @onCreate: is called when the activity is created. It sets up the app's UI and handles the camera permission request
 * CashManagerInterfaceTheme: defines the overall look of the app (colors, fonts and other visual elements)
 */
@ExperimentalPermissionsApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CashManagerInterfaceTheme {
                androidx.compose.material.Surface(color = MaterialTheme.colors.background) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        val cameraPermissionState = rememberPermissionState(permission = android.Manifest.permission.CAMERA)

                        Button(
                            onClick = {
                                cameraPermissionState.launchPermissionRequest()
                            }
                        ) {
                            Text(text = "Camera permission")
                        }

                        ProductScannerCameraPreview()
                    }
                }
            }
        }
    }
}
