package com.raul.ttwu.account.adaptor.output.persistence

import com.raul.ttwu.account.adaptor.output.persistence.extension.toDomain
import com.raul.ttwu.account.adaptor.output.persistence.extension.toEntity
import com.raul.ttwu.account.adaptor.output.persistence.repository.AccountRepository
import com.raul.ttwu.account.application.port.output.persistence.AccountPersistenceOutput
import com.raul.ttwu.account.domain.Account

class JpaAccountPersistenceOutput(
    private val repository: AccountRepository
): AccountPersistenceOutput {
    override fun save(account: Account): Account {
        val entity = repository.save(account.toEntity())
        return entity.toDomain()
    }
}