package com.epitech.cashmanagerinterface.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Product(
    val id: String,
    val name: String,
    val price: Float,
    val code: String,
    val imgUrl: String,
    val description: String
) {
}
