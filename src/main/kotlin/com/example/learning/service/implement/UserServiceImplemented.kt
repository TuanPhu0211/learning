package com.example.learning.service.implement

import com.example.learning.model.User
import com.example.learning.repository.UserRepository
import com.example.learning.service.UserService
import com.example.learning.service.security.SecurityUser
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImplemented(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder

) : UserService, UserDetailsService {
    override fun findById(userId: Long): User {
        return userRepository.findById(userId).orElseThrow { EntityNotFoundException("User not Found with $userId") }
    }

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    @Transactional
    override fun delete(user: User) {
        userRepository.delete(user)
    }

    @Transactional
    override fun deleteById(id: Long) {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id $id not found") }
        userRepository.delete(user)
    }

    override fun create(user: User): User {
        val user = userRepository.save(user)
        return user
    }

    override fun setPassword(password: String, user: User) {
        user.password = passwordEncoder.encode(password)
        userRepository.save(user)
    }

    override fun loadUserByUsername(username: String?): SecurityUser? {
        val user = userRepository.findByUsername(username!!) ?: throw Exception("User not found")
        return SecurityUser(user)
    }
}
