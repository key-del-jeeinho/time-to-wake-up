package com.raul.ttwu.account.adaptor.input.http.data.request

data class CreateAccountRequest(
    val name: String,
    val id: String,
    val password: String
)