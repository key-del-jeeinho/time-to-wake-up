package com.raul.ttwu.account.adaptor.input.http.data.response

data class CreateAccountResponse(
    val accountIdx: Long,
    val data: AccountDataResponse
)

data class AccountDataResponse(
    val name: String,
    val id: String
)