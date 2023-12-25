package com.epitech.cashmanagerinterface.features.productScanner.components

import android.annotation.SuppressLint
import android.util.Log
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.barcode.Barcode
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.TimeUnit

@SuppressLint("UnsafeOptInUsageError")
class BarcodeScanner (private val onBarcodeDetected: (barcodes: List<Barcode>) -> Unit, ): ImageAnalysis.Analyzer {

    private var lastAnalyzedTimestamp = 0L;
    override fun analyze(image: ImageProxy) {
        val currentTimestamp = System.currentTimeMillis()
        if (currentTimestamp - lastAnalyzedTimestamp >= TimeUnit.SECONDS.toMillis(1)) {
            image.image?.let { imageToAnalyze ->
                val options = BarcodeScannerOptions.Builder()
                    .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
                    .build()
                val barcodeScanner = BarcodeScanning.getClient(options)
                val imageToProcess = InputImage.fromMediaImage(imageToAnalyze, image.imageInfo.rotationDegrees)

                barcodeScanner.process(imageToProcess)
                    .addOnSuccessListener { barcodes ->
                        if (barcodes.isNotEmpty()) {
                            onBarcodeDetected(barcodes)
                        } else {
                            Log.d("TAG","No barcode scanned")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.d("TAG","Something went wrong $exception")
                    }
                    .addOnCompleteListener {
                        image.close()
                    }
            }
            lastAnalyzedTimestamp = currentTimestamp
        } else {
            image.close()
        }
    }
}