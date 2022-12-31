package com.raul.ttwu.account.adaptor.output.persistence.entity

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class AccountEntity(
    @Id var idx: Long,
    val name: String,
    val id: String,
    val encodedPassword: String,
) {
    val businessKey = "$idx - $name $id"
}