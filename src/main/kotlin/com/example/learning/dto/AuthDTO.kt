package com.example.learning.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class LoginRequestDTO(
    @field:NotBlank("Username is required")
    val username: String,

    @field:NotBlank("Password is required")
    val password: String
)

data class SignupRequestDTO(

    @field:NotBlank("Username is required")
    val username: String,

    @field:NotBlank("Password is required")
    @field:Size(min = 8, message = "Password must be at least 8 character")
    @field:Pattern(
        regexp = """^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[!@#\$%^&*()_\-+=\[\]{};':"\\|,.<>/?]).{8,}$""",
        message = "Password must contain at least 1 lowercase, 1 uppercase, 1 number, 1 special character, and be 8+ characters"
    )
    val password: String,

    val email: String
)