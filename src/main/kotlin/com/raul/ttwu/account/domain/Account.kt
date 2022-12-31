package com.raul.ttwu.account.domain

data class Account(
    val idx: Long,
    val name: String,
    val id: String,
    val encodedPassword: String
)