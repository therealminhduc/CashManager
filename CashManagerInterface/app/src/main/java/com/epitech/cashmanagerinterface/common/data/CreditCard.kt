package com.epitech.cashmanagerinterface.common.data

import kotlinx.serialization.Serializable

@Serializable
data class CreditCard(
    val cardNumber: String,
    val cardOwner: String,
    val securityNumber: String,
    val expirationDate: String
)
