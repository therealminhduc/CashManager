package com.epitech.cashmanagerinterface.common.data

import kotlinx.serialization.Serializable

@Serializable
data class AddProductRequest(
    val productCode: String,
    val quantity: Int
)
