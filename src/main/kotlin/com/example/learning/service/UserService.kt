package com.example.learning.service

import com.example.learning.model.User

interface UserService {
    fun findById(userId: Long): User?
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
    fun save(user: User): User
    fun delete(user: User)
    fun deleteById(id: Long)
    fun setPassword(password: String, user: User)
    fun create(user: User): User
}