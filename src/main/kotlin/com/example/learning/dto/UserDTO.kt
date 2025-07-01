package com.example.learning.dto

data class UserResponseDTO (
    val id: Long,
    val username: String,
    val email: String,
    val role: String? = null
)


