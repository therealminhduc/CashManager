package com.epitech.cashmanagerinterface.features.productScanner

import android.Manifest
import android.content.Context
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreConstants
import com.epitech.cashmanagerinterface.common.data.local.PreferenceDataStoreHelper
import com.epitech.cashmanagerinterface.features.cart.CartViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun CameraPermissionScreen(cartViewModel: CartViewModel = viewModel(), scaffoldState: ScaffoldState, context: Context) {
    val cameraPermissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)
    var userId by remember { mutableStateOf("null") }
    val preferenceDataStoreHelper = PreferenceDataStoreHelper(context)

    LaunchedEffect(cameraPermissionState) {
        userId = preferenceDataStoreHelper.getPreference(PreferenceDataStoreConstants.USERID_KEY, "null")
        if (!cameraPermissionState.status.isGranted) {
            cameraPermissionState.launchPermissionRequest()
        }
    }

    ProductScannerCameraPreview(cartViewModel = cartViewModel, scaffoldState = scaffoldState, userId = userId)
}