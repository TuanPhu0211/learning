package com.example.learning.config

import com.example.learning.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.Date
import javax.crypto.SecretKey


@Component
class JwtUtils(
    @Value("\${jwt.secret}") private val secret: String,
    @Value("\${jwt.expiration}") private val jwtExpirationMs: String,
) {

    lateinit var key: Key


    fun key(): Key {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret))
    }

    fun generateToken(user: User): String {
        val now = Date()
        val expiry = Date(now.time + jwtExpirationMs.toLong())
        val key = key()
        return Jwts.builder()
            .subject(user.username)
            .issuedAt(now)
            .expiration(expiry)
            .signWith(key)
            .compact()
    }

    fun getPayLoad(token: String): Claims? {
        try {
            val key = key() as SecretKey
            return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload

        }catch (ex: JwtException){
            return null
        }
    }


    fun validateToken(token: String): Boolean {
        val key = Keys.hmacShaKeyFor(jwtExpirationMs.toByteArray())
        try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
            return true
        }catch (e: JwtException){
            return false
        }
    }

}