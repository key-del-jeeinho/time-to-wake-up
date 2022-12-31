package com.raul.ttwu.account.adaptor.input.http.controller

import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import com.raul.ttwu.account.adaptor.input.http.data.response.AccountDataResponse
import com.raul.ttwu.account.adaptor.input.http.data.response.CreateAccountResponse
import com.raul.ttwu.account.adaptor.input.http.extension.toDomain
import com.raul.ttwu.account.application.port.input.AccountUseCase
import org.springframework.http.ResponseEntity

class AccountController(
    private val accountUseCase: AccountUseCase
) {
    fun createAccount(request: CreateAccountRequest): ResponseEntity<CreateAccountResponse> {
        val account = accountUseCase.createAccount(request.toDomain())
        return AccountDataResponse(id = account.id, name = account.name)
            .let { data -> CreateAccountResponse(account.idx, data) }
            .let { response -> ResponseEntity.ok(response) }
    }
}