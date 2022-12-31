package com.raul.ttwu.account.adaptor.input.http.controller

import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.longs.shouldBeGreaterThanOrEqual
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus

class AccountControllerTest: BehaviorSpec({
    given("create account request") {
        val controller = AccountController()
        val request = CreateAccountRequest("홍길동", "et_asdmxiv", "pw_vsiddf")

        `when`("is received") {
            val result = controller.createAccount(request)
            val body = result.body!!

            then("response status should be OK") {
                result.statusCode shouldBe HttpStatus.OK
            }

            then("account idx in response should not be negative") {
                body.accountIdx shouldBeGreaterThanOrEqual 0
            }

            then("account data in response should be same as request") {
                body.data.id shouldBe request.id
                body.data.name shouldBe request.name
            }
        }
    }
})