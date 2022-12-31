package com.raul.ttwu.account.application.service

import com.raul.ttwu.account.application.port.output.PasswordEncodeOutput
import com.raul.ttwu.account.application.port.output.persistence.AccountPersistenceOutput
import com.raul.ttwu.account.application.service.AccountService.Companion.INITIAL_ACCOUNT_IDX
import com.raul.ttwu.account.domain.Account
import com.raul.ttwu.account.domain.CreateAccount
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class AccountServiceTest: BehaviorSpec({
    val passwordEncodeOutput = mockk<PasswordEncodeOutput>()
    val accountPersistenceOutput = mockk<AccountPersistenceOutput>()
    val accountService = AccountService(passwordEncodeOutput, accountPersistenceOutput)
    given("create account") {
        val password = "pw_vsiddf"
        val encodedPassword = "epw_vsiddf"
        val createAccount = CreateAccount("홍길동", "id_asdmxiv", password)
        val account = Account(INITIAL_ACCOUNT_IDX, "홍길동", "id_asdmxiv", encodedPassword)
        val accountIdx = 1L

        `when`("is invoked") {
            every { passwordEncodeOutput.encode(password) } returns encodedPassword
            every { accountPersistenceOutput.save(account) } returns account.copy(idx = accountIdx)
            val result = accountService.createAccount(createAccount)

            then("password should be encoded") {
                verify { passwordEncodeOutput.encode(password) }
            }

            then("account should be created with context of create account") {
                verify(exactly = 1) { accountPersistenceOutput.save(account) }
            }

            then("result should be in context of create account") {
                result.idx shouldBe accountIdx
                result.name shouldBe createAccount.name
                result.id shouldBe createAccount.id
                result.encodedPassword shouldBe encodedPassword
            }
        }
    }
})