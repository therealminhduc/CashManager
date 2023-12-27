package com.epitech.cashmanagerinterface.features.productScanner.components

import android.Manifest
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.font.FontStyle
import com.epitech.cashmanagerinterface.features.productScanner.ProductScannerCameraPreview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun CameraPermission() {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    LaunchedEffect(cameraPermissionState) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

//    if (!cameraPermissionState.hasPermission) {
//        Text(
//            text = "Please allow camera access from your device settings.",
//            fontStyle = FontStyle.Italic,
//        )
//    }

    ProductScannerCameraPreview()
}