package com.raul.ttwu.account.application.service.extension

import com.raul.ttwu.account.domain.Account
import com.raul.ttwu.account.domain.CreateAccount

fun CreateAccount.aggregateAccount(initialAccountIdx: Long, encodedPassword: String): Account =
    Account(idx = initialAccountIdx, name = name, id = id, encodedPassword = encodedPassword)