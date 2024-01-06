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

    suspend fun login(user: String): String {
        return try {
            val loginUser: HttpResponse = client.post("${ApiClient.BASE_URL}/auth/login") {
                contentType(ContentType.Application.Json)
                var jsonObj = Json.parseToJsonElement(user)
                body = jsonObj
            }

            val responseBody = loginUser.readText()
            Log.d("TAG", "Login response: $responseBody")

            responseBody
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

    suspend fun validateBasket(cardInfos: String): String {
        return try {
            val validBasket: HttpResponse = client.post("${ApiClient.BASE_URL}/users/6598494e33ec974bc5ef3eac/basket/validate") {
                contentType(ContentType.Application.Json)
                var jsonObj = Json.parseToJsonElement(cardInfos)
                body = jsonObj
            }

            val responseBody = validBasket.readText()
            Log.d("TAG", "Login response: $responseBody")

            responseBody
        } catch (e: Exception) {
            Log.e("TAG", "Error during login: $e")
            throw e
        }
    }
}