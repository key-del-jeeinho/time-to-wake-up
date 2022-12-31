package com.raul.ttwu.account

import com.raul.ttwu.IntegrationTestBase
import com.raul.ttwu.account.adaptor.input.http.data.request.CreateAccountRequest
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.stream.Stream

class AccountIntegrationTest: IntegrationTestBase() {
    companion object {
        @JvmStatic
        fun accountData(): Stream<Arguments> = Stream.of(
            Arguments.arguments("홍길동", "et_asdmxiv", "pw_vsiddf"),
            Arguments.arguments("김홍도", "et_shnbdf", "pw_usd"),
            Arguments.arguments("김삿갓", "et_ug", "pw_pasdsfgms"),
            Arguments.arguments("로버트.C.마틴", "et_s0ijfav.sad", "pw_wgefjs"),
            Arguments.arguments("아무개", "et_mwrmj", "pw_ofhrem"),
        )
    }

    /*
    feature: create account
    #1 success
    #2 failure (id already exists)
    #4 failure (password is not valid)
     */
    @ParameterizedTest
    @DisplayName("create account #1 success")
    @MethodSource("accountData")
    fun `create account #1 success`(name: String, id: String, password: String) {
        //given
        val request = CreateAccountRequest(name, id, password)
        //when
        val resultAction = mvc.perform(post("/api/v1/accounts")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .accept(APPLICATION_JSON))
                .andDo{ print(it.response.contentAsString) }
        //then
        resultAction
            .andExpect(status().isOk)
            .andExpect(jsonPath("accountIdx", `is`(notNullValue())))
            .andExpect(jsonPath("data.name", `is`(name)))
            .andExpect(jsonPath("data.id", `is`(id)))
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
}