package com.raul.ttwu.account.adaptor.input.http.controller

import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import com.raul.ttwu.account.application.port.input.AccountUseCase
import com.raul.ttwu.account.domain.Account
import com.raul.ttwu.account.domain.CreateAccount
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.springframework.http.HttpStatus

class AccountControllerTest: BehaviorSpec({
    val accountUseCase = mockk<AccountUseCase>()
    val controller = AccountController(accountUseCase)
    given("create account request") {
        val request = CreateAccountRequest("홍길동", "id_asdmxiv", "pw_vsiddf")
        val createAccount = CreateAccount(request.name, request.id, request.password)
        val account = Account(0, "_홍길동", "_id_asdmxiv", "_epw_vsiddf")

        `when`("is received") {
            every { accountUseCase.createAccount(createAccount) } returns account
            val result = controller.createAccount(request)
            val body = result.body!!

            then("business logic in use case should be called") {
                verify(exactly = 1) { accountUseCase.createAccount(createAccount) }
            }

            then("response status should be OK") {
                result.statusCode shouldBe HttpStatus.OK
            }

            then("account data in response should be same as return of service") {
                body.accountIdx shouldBe account.idx
                body.data.id shouldBe account.id
                body.data.name shouldBe account.name
            }
        }
    }
})