package com.epitech.cashmanagerinterface.common.data

import kotlinx.serialization.Serializable

@Serializable
data class ProductCodeRequest(
    val productCode: String
)
