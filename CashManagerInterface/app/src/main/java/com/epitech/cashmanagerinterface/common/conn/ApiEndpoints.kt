package com.epitech.cashmanagerinterface.common.conn

import android.util.Log
import com.epitech.cashmanagerinterface.common.data.Product
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class ApiEndpoints(private val client: HttpClient) {

    suspend fun getProductByCode(code: String): Product? {
        return try {
            val product = client.get<Product>("${ApiClient.BASE_URL}/products/$code")
            Log.d("TAG", "Product: $product")
            product
        } catch (e: Exception) {
            Log.e("TAG", "Error fetching product: $e")
            null
        }
    }
}