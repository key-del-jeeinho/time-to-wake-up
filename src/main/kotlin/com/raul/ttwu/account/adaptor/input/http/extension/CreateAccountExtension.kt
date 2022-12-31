package com.raul.ttwu.account.adaptor.input.http.extension

import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import com.raul.ttwu.account.domain.CreateAccount

fun CreateAccountRequest.toDomain(): CreateAccount = CreateAccount(name = name, id = id, password = password)