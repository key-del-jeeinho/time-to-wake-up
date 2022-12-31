package com.raul.ttwu.account.application.port.output

interface PasswordEncodeOutput {
    fun encode(password: String): String

}
