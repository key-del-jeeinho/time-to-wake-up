package com.raul.ttwu

import com.fasterxml.jackson.databind.ObjectMapper
import io.kotest.core.spec.style.BehaviorSpec
import org.junit.jupiter.api.Disabled
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Disabled
@Transactional
@AutoConfigureMockMvc
class IntegrationTestSpec(
    mvc: MockMvc,
    objectMapper: ObjectMapper,
    body: BehaviorSpec.() -> Unit = {}
): BehaviorSpec(body)