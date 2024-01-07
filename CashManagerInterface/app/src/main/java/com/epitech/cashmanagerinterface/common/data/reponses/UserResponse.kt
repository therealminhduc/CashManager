package com.epitech.cashmanagerinterface.common.data.reponses

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: String,
    val username: String,
    val password: String,
    val basket: BasketResponse
)
