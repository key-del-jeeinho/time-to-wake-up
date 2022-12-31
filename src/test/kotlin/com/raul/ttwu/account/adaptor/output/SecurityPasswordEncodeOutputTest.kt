package com.raul.ttwu.account.adaptor.output

import io.kotest.core.spec.style.ExpectSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.security.crypto.password.PasswordEncoder

class SecurityPasswordEncodeOutputTest: ExpectSpec({
    val passwordEncoder = mockk<PasswordEncoder>()
    val securityPasswordEncodeOutput = SecurityPasswordEncodeOutput(passwordEncoder)

    context("password") {
        val password = "pw_vsiddf"

        context("encode") {
            val encodedPassword = "epw_vsiddf"
            every { passwordEncoder.encode(password) } returns encodedPassword

            expect("is delegated to password encoder") {
                val result = securityPasswordEncodeOutput.encode(password)
                verify(exactly = 1) { passwordEncoder.encode(password) }
                println("result: $result")
            }
        }
    }
})