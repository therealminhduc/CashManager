package com.epitech.cashmanagerinterface.common.conn

import android.util.Log
import com.epitech.cashmanagerinterface.common.data.Product
import com.epitech.cashmanagerinterface.common.data.User
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json

class ApiEndpoints(private val client: HttpClient) {

    suspend fun login(user: String): HttpResponse {
        return try {
            val loginUser: HttpResponse = client.post("${ApiClient.BASE_URL}/auth/login") {
                contentType(ContentType.Application.Json)
                var jsonObj = Json.parseToJsonElement(user)
                body = jsonObj
            }

            val responseBody = loginUser.readText()
            Log.d("TAG", "Login response: $responseBody")

            loginUser
        } catch (e: Exception) {
            Log.e("TAG", "Error during login: $e")
            throw e
        }
    }

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