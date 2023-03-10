package com.raul.ttwu.account.adaptor.input.http.controller

import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import com.raul.ttwu.account.adaptor.input.http.data.response.AccountDataResponse
import com.raul.ttwu.account.adaptor.input.http.data.response.CreateAccountResponse
import com.raul.ttwu.account.adaptor.input.http.extension.toDomain
import com.raul.ttwu.account.application.port.input.AccountUseCase
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/accounts")
class AccountController(
    private val accountUseCase: AccountUseCase
) {
    @PostMapping
    fun createAccount(@RequestBody request: CreateAccountRequest): ResponseEntity<CreateAccountResponse> {
        val account = accountUseCase.createAccount(request.toDomain())
        return AccountDataResponse(id = account.id, name = account.name)
            .let { data -> CreateAccountResponse(account.idx, data) }
            .let { response -> ResponseEntity.ok(response) }
    }
}