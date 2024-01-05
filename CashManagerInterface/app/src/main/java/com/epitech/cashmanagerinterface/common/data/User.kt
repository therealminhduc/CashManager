package com.epitech.cashmanagerinterface.common.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String = "",
    val password: String = ""
)
