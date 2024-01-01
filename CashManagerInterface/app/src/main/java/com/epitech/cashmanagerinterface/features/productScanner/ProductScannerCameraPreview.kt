package com.epitech.cashmanagerinterface.features.productScanner

import android.annotation.SuppressLint
import android.util.Log
import android.view.ViewGroup
import android.widget.Space
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import com.epitech.cashmanagerinterface.R
import com.epitech.cashmanagerinterface.common.conn.ApiClient
import com.epitech.cashmanagerinterface.common.conn.ApiEndpoints
import com.epitech.cashmanagerinterface.common.data.Product
import com.epitech.cashmanagerinterface.features.productScanner.components.BarcodeScanner
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


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
@SuppressLint("RememberReturnType", "CoroutineCreationDuringComposition",
    "StateFlowValueCalledInComposition"
)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@androidx.compose.ui.tooling.preview.Preview
fun ProductScannerCameraPreview() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    var preview by remember {
        mutableStateOf<Preview?>(null)
    }

    val barCodeVal = remember {
        mutableStateOf("")
    }

    val barCodeValueHost = remember {
        mutableStateOf("")
    }

    var isSheetOpen by rememberSaveable {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState()

    var currentBarCodeValue by remember { mutableStateOf("") }

    var product by remember {
        mutableStateOf<Product?>(null)
    }

    var apiEndpoints = remember {
        ApiClient.createApiEndpoints()
    }

    AndroidView(
        factory = { AndroidViewContext ->
            val previewView = PreviewView(AndroidViewContext)
            previewView.scaleType = PreviewView.ScaleType.FILL_CENTER
            previewView.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            previewView.implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            return@AndroidView previewView
        },
        modifier = Modifier
            .fillMaxSize(),
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

                val productScanner = BarcodeScanner { barcodes ->
                    barcodes.forEach { barcode ->
                        barcode.rawValue?.let { barCodeValue ->
                            barCodeValueHost.value = barCodeValue
                            barCodeVal.value = barCodeValue
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

    LaunchedEffect(barCodeValueHost.value, sheetState) {
        val newBarCodeValue = barCodeValueHost.value
        if (newBarCodeValue.isNotEmpty() && newBarCodeValue != currentBarCodeValue) {
            currentBarCodeValue = newBarCodeValue
            isSheetOpen = true

        product = coroutineScope { apiEndpoints.getProductByCode(newBarCodeValue) }

        } else if (newBarCodeValue.isEmpty() && isSheetOpen) {
            isSheetOpen = false
        }

        Log.d("TAG", "newBarCodeValue $newBarCodeValue currentBarCodeValue $currentBarCodeValue product $product")
    }

    if (isSheetOpen) {
        ModalBottomSheet(
            sheetState = sheetState,
            modifier = Modifier
                .height(250.dp)
                .fillMaxSize(),
            onDismissRequest = {
                isSheetOpen = false
                currentBarCodeValue = ""
            },
        ) {
            if (product != null) {
                Row (modifier = Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                    ) {
                    Column (modifier = Modifier
                        .padding(end = 16.dp)
                    ){
                        Text(text = "${product?.name}", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                        Text(text = "${product?.price}€", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                        Text(text = "${product?.description}")
                    }

                    Column(modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .size(100.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White),
                            model = product?.imgUrl,
                            contentDescription = null
                        )
                        
                        Spacer(modifier = Modifier.height(10.dp))

                        Button(
                            modifier = Modifier
                                .width(70.dp)
                                .height(30.dp),
                            onClick = {
                                isSheetOpen = true
                            }
                        ) {
                            Text(text = "Add", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Product $currentBarCodeValue is not available",
                        style = MaterialTheme.typography.labelLarge,
                        color = Color(220, 20, 60),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

//    Log.d("TAG", "isSheetOpen $isSheetOpen sheetState ${sheetState.currentValue} currentBarCodeValue $currentBarCodeValue")
}