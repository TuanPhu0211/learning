package com.example.learning.controller

import com.example.learning.dto.UserResponseDTO
import com.example.learning.service.implement.UserServiceImplemented
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    val userServiceImplemented: UserServiceImplemented
) {
    @GetMapping("{id}")
    fun getUser(@PathVariable id: Long): ResponseEntity<UserResponseDTO> {
        val user  = userServiceImplemented.findById(id)
        return ResponseEntity.ok(UserResponseDTO(
            user.id!!,
            user.username,
            user.email,
            user.role.name
        ))
    }
}