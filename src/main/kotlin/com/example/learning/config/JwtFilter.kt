package com.example.learning.config

import com.example.learning.service.implement.UserServiceImplemented
import com.example.learning.service.security.SecurityUser
import io.micrometer.common.util.StringUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.filter.OncePerRequestFilter

class JwtFilter(
    private val jwtUtils: JwtUtils,
    private val userServiceImplemented: UserServiceImplemented
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            val jwt = getJWT(request)
            if (jwt != null && jwtUtils.validateToken(jwt)) {
                val payload = jwtUtils.getPayLoad(jwt)
                val userDetails = userServiceImplemented.loadUserByUsername(payload?.subject)
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails?.authorities)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }catch (e:Exception){

        }
        filterChain.doFilter(request, response)
    }

    fun getJWT(request: HttpServletRequest): String? {
        val heaederAuth = request.getHeader("Authorization")
        if (StringUtils.isNotEmpty(heaederAuth) && heaederAuth.startsWith("Bearer ")) {
            return heaederAuth.substring(7).trim()
        }
        return null
    }

}