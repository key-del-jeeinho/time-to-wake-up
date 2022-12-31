package com.raul.ttwu.account.adaptor.output.persistence

import com.raul.ttwu.account.adaptor.output.persistence.entity.AccountEntity
import com.raul.ttwu.account.adaptor.output.persistence.repository.AccountRepository
import com.raul.ttwu.account.domain.Account
import io.kotest.core.spec.style.ExpectSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class JpaAccountPersistenceOutputTest: ExpectSpec({
    val accountRepository = mockk<AccountRepository>()
    val jpaAccountPersistenceOutput = JpaAccountPersistenceOutput(accountRepository)
    context("account") {
        val account = Account(0L, "홍길동", "id_asdmxiv", "epw_vsiddf")
        val entity = AccountEntity(account.idx, account.name, account.id, account.encodedPassword)

        context ("save") {
            val savedEntity = entity.apply { idx = 1L }
            every { accountRepository.save(match{ it.eq(entity) }) } returns savedEntity

            expect("is delegated to repository") {
                val result = jpaAccountPersistenceOutput.save(account)
                verify(exactly = 1) { accountRepository.save(match{ it.eq(entity) }) }
                println("result: $result")
            }
        }
    }
})

private fun AccountEntity.eq(it: AccountEntity): Boolean =
    this.businessKey == it.businessKey