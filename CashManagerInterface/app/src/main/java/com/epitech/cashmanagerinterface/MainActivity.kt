package com.epitech.cashmanagerinterface

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import com.epitech.cashmanagerinterface.features.productScanner.productScanner
import com.epitech.cashmanagerinterface.ui.theme.CashManagerInterfaceTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import com.google.common.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.jar.Manifest


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

                        CameraPreview()
                    }
                }
            }
        }
    }
}

/**
 * CameraPreview(): render the camera preview, handles the barcodes detector
 *
 * @context: retrieves the current Android app context from the composable function's scope
 * -> to access resources, to launch activities
 *
 * @lifecycleOwner: reference to the current lifecycle owner for the composable function's scope
 * -> allows the composable function to access & manage the lifecycle of the enclosing composable function or the app itself
 *
 * @preview: mutable state variable (data can be modified after it is created)
 * -> represent the camera preview and its associated data
 * -> connect the camera preview to the previewView
 * -> receives updates from the camera preview
 *
 * @barCodeVal: String that changes on barcode dectection
 * -> used to display the value of the latest barcode that was detected by the camera
 *
 * @AndroidView: View that displays the PreviewView
 * @PreviewView: View that displays the camera preview
 *
 * @cameraExecutor: use single thread to process the frames captured by the camera
 * @cameraProviderFuture: retrieves ONLY 1 instance of ProcessCameraProvider per app
 *
 * @setBackpressureStrategy: ensures that only the latest camera frame is analyzed
 *
 * @cameraProvider.unbindAll(): unbinds all previously bound use cases from the cameraProvider
 * -> ensures that no resources are leaked when the camera preview is destroyed
 * @cameraProvider.bindToLifecycle( lifecycleOwner, cameraSelector, preview, imageAnalysis ): binds the camera preview and image analysis to the lifecycle of the lifecycleOwner
 * -> automatically manages the lifecycle of the camera preview and image analysis, ensures that they're only created & destroyed when needed
 */
@Composable
fun CameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember {
        mutableStateOf<Preview?>(null)
    }
    val barCodeVal = remember {
        mutableStateOf("")
    }

    AndroidView(
        factory = { AndroidViewContext ->
            PreviewView(AndroidViewContext).apply {
                this.scaleType = PreviewView.ScaleType.FILL_CENTER
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
                implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            }
        },
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        update = { previewView ->
            val cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build()
            val cameraExecutor: ExecutorService = Executors.newSingleThreadExecutor()
            val cameraProviderFuture: ListenableFuture<ProcessCameraProvider> = ProcessCameraProvider.getInstance(context)

            cameraProviderFuture.addListener({
                preview = Preview.Builder().build().also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

                val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
                val productScanner = productScanner { barcodes ->
                    barcodes.forEach { barcode ->
                        barcode.rawValue?.let { barCodeValue ->
                            barCodeVal.value = barCodeValue
                            Toast.makeText(context, barCodeValue, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                val imageAnalysis: ImageAnalysis = ImageAnalysis.Builder()
                    .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                    .build()
                    .also {
                        it.setAnalyzer(cameraExecutor, productScanner)
                    }

                try {
                    cameraProvider.unbindAll()
                    cameraProvider.bindToLifecycle(
                        lifecycleOwner,
                        cameraSelector,
                        preview,
                        imageAnalysis
                    )
                } catch (exception: Exception) {
                    Log.d("TAG", "Camera Preview: ${exception.localizedMessage}")
                }
            }, ContextCompat.getMainExecutor(context))
        }
    )
}