package com.epitech.cashmanagerinterface.common.conn

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.post


// singleton object: create 1 instance and can be accessed from anywhere in project
object ApiClient {
//    const val BASE_URL = "https://127.0.0.1:8080/api"
    const val BASE_URL = "http://10.0.2.2:8080/api"

    val httpClient: HttpClient by lazy {
        HttpClient {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
    }

    fun createApiEndpoints(): ApiEndpoints {
        return ApiEndpoints(httpClient)
    }

    suspend inline fun <reified T> get(endpoint: String): T {
        return httpClient.get("$BASE_URL$endpoint")
    }

    suspend inline fun <reified T> post(endpoint: String, body: Any): T {
        return httpClient.post("$BASE_URL$endpoint") {
            this.body = body
        }
    }
}