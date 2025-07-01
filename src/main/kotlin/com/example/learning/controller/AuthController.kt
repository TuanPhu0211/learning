package com.example.learning.controller

import com.example.learning.dto.LoginRequestDTO
import com.example.learning.dto.UserResponseDTO
import com.example.learning.service.implement.UserServiceImplemented
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/")
class AuthController(
    private val userServiceImplemented: UserServiceImplemented, private val passwordEncoder: PasswordEncoder
) {
    @PostMapping("/login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ResponseEntity<*> {
        val user = userServiceImplemented.findByUsername(loginRequestDTO.username)
            ?: throw UsernameNotFoundException("User not found")
        if (passwordEncoder.matches(loginRequestDTO.password, user.password)) {
            return ResponseEntity.ok(UserResponseDTO(user.id!!, user.username, user.password, user.role.name))
        }
        return ResponseEntity.status(400).body(mapOf("error" to "Wrong Password"))
    }
}