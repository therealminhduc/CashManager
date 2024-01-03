package com.epitech.cashmanagerinterface.common.conn

import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.get
import io.ktor.client.request.post
import java.util.Properties


// singleton object: create 1 instance and can be accessed from anywhere in project
object ApiClient {
    private const val PROPERTIES_FILE = "/res/raw/config.properties"

    private val properties: Properties by lazy {
        val prop = Properties()
        val inputStream = ApiClient::class.java.getResourceAsStream(PROPERTIES_FILE)
        prop.load(inputStream)
        prop
    }

//    const val BASE_URL = "https://127.0.0.1:8080/api"
//    const val BASE_URL = "http://10.0.2.2:8080/api"
    val BASE_URL: String = properties.getProperty("api.base_url")

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