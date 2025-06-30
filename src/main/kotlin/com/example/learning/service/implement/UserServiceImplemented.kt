package com.example.learning.service.implement

import com.example.learning.model.User
import com.example.learning.repository.UserRepository
import com.example.learning.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
@Service
class UserServiceImplemented(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) : UserService {

    override fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }

    override fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    override fun save(user: User): User {
        return userRepository.save(user)
    }

    override fun delete(user: User) {
        userRepository.delete(user)
    }

    override fun deleteById(id: Long) {
        val user = userRepository.findById(id)
            .orElseThrow { NoSuchElementException("User with id $id not found") }
        userRepository.delete(user)
    }

    override fun create(user: User): User {
        val user = userRepository.save(user)
    }

    override fun setPassword(password: String, user: User) {
        user.password = passwordEncoder.encode(password)
        userRepository.save(user)
    }
}
