package com.raul.ttwu.account.application.service

import com.raul.ttwu.account.application.port.input.AccountUseCase
import com.raul.ttwu.account.application.port.output.PasswordEncodeOutput
import com.raul.ttwu.account.application.port.output.persistence.AccountPersistenceOutput
import com.raul.ttwu.account.application.service.extension.aggregateAccount
import com.raul.ttwu.account.domain.Account
import com.raul.ttwu.account.domain.CreateAccount
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val passwordEncodeOutput: PasswordEncodeOutput,
    private val accountPersistenceOutput: AccountPersistenceOutput
): AccountUseCase {
    companion object {
        const val INITIAL_ACCOUNT_IDX = 0L
    }

    override fun createAccount(domain: CreateAccount): Account {
        val encodedPassword = passwordEncodeOutput.encode(domain.password)
        val account = domain.aggregateAccount(INITIAL_ACCOUNT_IDX, encodedPassword)
        val savedAccount = accountPersistenceOutput.save(account)
        return savedAccount
    }
}