package com.raul.ttwu.account.application.port.input

import com.raul.ttwu.account.domain.Account
import com.raul.ttwu.account.domain.CreateAccount

interface AccountUseCase {
    fun createAccount(dto: CreateAccount): Account
}
