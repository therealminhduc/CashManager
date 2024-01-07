package com.epitech.cashmanagerinterface.common.data.reponses

import com.epitech.cashmanagerinterface.common.data.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BasketResponse(
    @SerialName("id") val id: String,
    @SerialName("products") val products: List<Product>,
    @SerialName("value") val value: Double,
    )
