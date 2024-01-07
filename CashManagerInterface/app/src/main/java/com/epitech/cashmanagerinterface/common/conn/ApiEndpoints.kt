package com.epitech.cashmanagerinterface.common.conn

import android.util.Log
import com.epitech.cashmanagerinterface.common.data.Product
import com.epitech.cashmanagerinterface.common.data.reponses.BasketResponse
import com.epitech.cashmanagerinterface.common.data.reponses.UserResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.receive
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
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
    suspend fun register(user: String): HttpResponse {
        return try {
            val RegisterUser: HttpResponse = client.post("${ApiClient.BASE_URL}/users") {
                contentType(ContentType.Application.Json)
                var jsonObj = Json.parseToJsonElement(user)
                body = jsonObj
            }

            val responseBody = RegisterUser.readText()
            Log.d("TAG", "Login response: $responseBody")

            RegisterUser
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

    suspend fun getUserById(userid: String): UserResponse? {
        return try {
            val user = client.get<UserResponse>("${ApiClient.BASE_URL}/users/$userid")
            Log.d("TAG", "User: $user")
            user
        } catch (e: Exception) {
            Log.e("TAG", "Error fetching user: $e")
            null
        }
    }

    suspend fun validateBasket(userId: String, cardInfos: String): String {
        return try {
            val validBasket: HttpResponse = client.post("${ApiClient.BASE_URL}/users/$userId/basket/validate") {
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

    suspend fun getUsersBasket(userId: String): List<Product> {
        return try {
            val response: HttpResponse = client.get("${ApiClient.BASE_URL}/users/$userId/basket")

            if (response.status.isSuccess()) {
                val basketResponse: BasketResponse = response.receive()
                return basketResponse.products
            } else {
                Log.e("Get Users Basket", "Error getting the user basket. Status code: ${response.status}")
                emptyList()
            }

        } catch (e: Exception) {
            Log.e("Get Users Basket", "Error getting the user basket: $e")
            throw e
        }
    }

    suspend fun deleteAllProductsWithCode(userId: String, code: String): List<Product> {
        emptyList<Product>()
        return try {
            val response: HttpResponse = client.delete("${ApiClient.BASE_URL}/users/$userId/basket/products") {
                contentType(ContentType.Application.Json)
                var jsonObj = Json.parseToJsonElement(code)
                body = jsonObj
            }
            emptyList<Product>()
        } catch (e: Exception) {
            Log.e("Delete All products with code", "Error deleting products: $e")
            throw e
        }
    }

    suspend fun addProductToBasket(userId: String, productRequest: String): Product? {
        return try {
            val response: HttpResponse = client.post("${ApiClient.BASE_URL}/users/$userId/basket/product") {
                contentType(ContentType.Application.Json)
                var jsonObj = Json.parseToJsonElement(productRequest)
                body = jsonObj
            }
            Product("23", "placeholder", 23f, "213123", "utlll", "coooool")
        } catch (e: Exception) {
            Log.e("Add product to basket", "Error adding the product $productRequest to the basket: $e")
            return null
        }
    }
}