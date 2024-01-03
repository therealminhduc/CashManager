package com.epitech.cashmanagerinterface.features.productScanner

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.epitech.cashmanagerinterface.features.productScanner.ProductScannerCameraPreview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun CameraPermissionScreen(cartViewModel: CartViewModel) {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

    LaunchedEffect(cameraPermissionState) {
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    ProductScannerCameraPreview(cartViewModel = cartViewModel)
}