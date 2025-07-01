package com.example.learning.config

import com.example.learning.service.implement.UserServiceImplemented
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SecurityConfig(
    private val userServiceImplemented: UserServiceImplemented,
    private val passwordEncoder: PasswordEncoder,
    private val JwtFilter: JwtFilter
) {

    @Bean
    fun authenticationManager(authConfig: AuthenticationConfiguration): AuthenticationManager {
        return authConfig.getAuthenticationManager()
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests{
                it.requestMatchers("/admin/**").hasRole("ADMIN")
                it.requestMatchers("/api/admin/**").hasRole("ADMIN")
                it.requestMatchers("/api/user/**").authenticated()
                it.requestMatchers("/api/auth/login", "/api/auth/signup").permitAll()
                it.requestMatchers(HttpMethod.GET,"/api/blog/**").permitAll()
                it.anyRequest().authenticated()
            }
            .addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter::class.java)
        return http.build()
    }
}