package com.raul.ttwu.account.adaptor.input.http.controller

import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import com.raul.ttwu.account.adaptor.input.http.data.response.CreateAccountResponse
import org.springframework.http.ResponseEntity

class AccountController {
    fun createAccount(request: CreateAccountRequest): ResponseEntity<CreateAccountResponse> {
        TODO("Not yet implemented")
    }
}