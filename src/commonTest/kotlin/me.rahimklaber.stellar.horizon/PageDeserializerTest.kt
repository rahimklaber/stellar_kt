package me.rahimklaber.stellar.horizon

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertIs


class PageDeserializerTest {
    val json = Json { ignoreUnknownKeys = true }

    @Test
    fun anAccountResponseShouldParseSuccessfully(){
        val decodeResponse = json.decodeFromString<Page<AccountResponse>>(accountResponses1)
        assertIs<Page<AccountResponse>>(decodeResponse)
    }

}