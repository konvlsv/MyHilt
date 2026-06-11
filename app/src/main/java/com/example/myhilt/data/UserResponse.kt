package com.example.myhilt.data

import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val name: String,
    val username: String,
    val email: String
)