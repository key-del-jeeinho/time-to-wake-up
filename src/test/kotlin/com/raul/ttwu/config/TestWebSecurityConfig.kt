package com.raul.ttwu.config

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class TestWebSecurityConfig {
    companion object {
        const val PASSWORD_ENCODE_TYPE_PATH = "test.security.password-encode"
        const val PASSWORD_ENCODE_TYPE_BCRYPT = "bcrypt"

        const val HTTP_AUTHORIZE_REQUEST_PROPERTY_PATH = "test.security.http.authorize-request"
        const val HTTP_AUTHORIZE_REQUEST_PROPERTY_PERMIT_ALL = "permit-all"
    }

    @Bean
    @ConditionalOnProperty(name = [HTTP_AUTHORIZE_REQUEST_PROPERTY_PATH], havingValue = HTTP_AUTHORIZE_REQUEST_PROPERTY_PERMIT_ALL)
    fun filterChain(http: HttpSecurity): SecurityFilterChain =
        http
            .csrf().disable()
            .cors().disable()
            .authorizeHttpRequests()
            .anyRequest().permitAll()
            .and()
            .build()

    @Bean
    @ConditionalOnProperty(name = [PASSWORD_ENCODE_TYPE_PATH], havingValue = PASSWORD_ENCODE_TYPE_BCRYPT)
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}