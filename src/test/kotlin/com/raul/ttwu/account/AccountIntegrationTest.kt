package com.raul.ttwu.account

import com.fasterxml.jackson.databind.ObjectMapper
import com.raul.ttwu.IntegrationTestSpec
import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class AccountIntegrationTest(
    mvc: MockMvc,
    objectMapper: ObjectMapper,
): IntegrationTestSpec(mvc, objectMapper, {

    /*
    feature: create account
    #1 success
    #2 failure (id already exists)
    #4 failure (password is not valid)
     */
    given("create account request (#1 success)") {
        val request = CreateAccountRequest("홍길동", "id_asdmxiv", "pw_vsiddf")
        `when`("is received") {
            val resultAction = mvc.perform(post("/api/v1/accounts")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(APPLICATION_JSON))
                .andDo{ print(it.response.contentAsString) }

            then("response status should be OK") {
                resultAction.andExpect(status().isOk)
            }

            then("response body should be in context of create account") {
                resultAction
                    .andExpect(jsonPath("accountIdx", `is`(notNullValue())))
                    .andExpect(jsonPath("data.name", `is`(request.name)))
                    .andExpect(jsonPath("data.id", `is`(request.id)))
            }
        }
    }

    /*
    feature: get all account data
    #1 success
     */

    /*
    feature: get account data by id
    #1 success
    #2 failure (account not found)
    #3 failure (not queryable data)
     */

    /*
    feature: change account data need to be authenticated
    #1 success
    #2 failure (account not found)
    #3 failure (not changeable data)
    #4 failure (permission denied)
     */

    /*
    feature: delete account by id need to be authenticated
    #1 success
    #2 failure (account not found)
    #3 failure (not deletable data)
    #4 failure (permission denied)
     */
})