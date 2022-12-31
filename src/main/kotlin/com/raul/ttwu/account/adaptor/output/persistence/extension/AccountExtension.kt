package com.raul.ttwu.account.adaptor.output.persistence.extension

import com.raul.ttwu.account.adaptor.output.persistence.entity.AccountEntity
import com.raul.ttwu.account.domain.Account


fun AccountEntity.toDomain(): Account = Account(
    idx = idx,
    name = name,
    id = id,
    encodedPassword = encodedPassword,
)

fun Account.toEntity(): AccountEntity = AccountEntity(
    idx = idx,
    name = name,
    id = id,
    encodedPassword = encodedPassword,
)