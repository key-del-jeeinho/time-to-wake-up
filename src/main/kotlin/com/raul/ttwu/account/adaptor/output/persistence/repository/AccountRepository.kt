package com.raul.ttwu.account.adaptor.output.persistence.repository

import com.raul.ttwu.account.adaptor.output.persistence.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository: JpaRepository<AccountEntity, Long> {
}
