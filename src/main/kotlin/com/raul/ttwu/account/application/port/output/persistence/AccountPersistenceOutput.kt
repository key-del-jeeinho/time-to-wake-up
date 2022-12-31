package com.raul.ttwu.account.application.port.output.persistence

import com.raul.ttwu.account.domain.Account

interface AccountPersistenceOutput {
    fun save(account: Account): Account
}
