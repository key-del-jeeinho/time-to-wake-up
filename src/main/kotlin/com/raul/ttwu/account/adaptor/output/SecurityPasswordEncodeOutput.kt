package com.raul.ttwu.account.adaptor.output

import com.raul.ttwu.account.application.port.output.PasswordEncodeOutput
import org.springframework.security.crypto.password.PasswordEncoder

class SecurityPasswordEncodeOutput(
    private val passwordEncoder: PasswordEncoder
): PasswordEncodeOutput {
    override fun encode(password: String): String {
        return passwordEncoder.encode(password)
    }
}